package hust.project.productservice.usecase;

import hust.project.common.event.ProductCreatedEvent;
import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.constants.ImageType;
import hust.project.productservice.entity.*;
import hust.project.productservice.entity.dto.request.CreateProductRequest;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.ProductMapper;
import hust.project.productservice.port.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateProductUseCase {
    private final IProductPort productPort;
    private final IBrandPort brandPort;
    private final ICategoryPort categoryPort;
    private final IImagePort imagePort;
    private final IProductCategoryPort productCategoryPort;
    private final IProductRelatedPort productRelatedPort;
    private final IProductEventPort productEventPort;

    @Transactional
    public ProductEntity createProduct(CreateProductRequest request) {
        // check brand, category, related product exist
        BrandEntity brand = null;
        if (request.getBrandId() != null) {
            brand = brandPort.getBrandById(request.getBrandId());
        }

        List<CategoryEntity> categories = null;
        if (!CollectionUtils.isEmpty(request.getCategoryIds())) {
            categories = categoryPort.getCategoriesByIds(request.getCategoryIds());

            if (categories.size() != request.getCategoryIds().size()) {
                log.error("[CreateProductUseCase] create product failed, categories not found");
                throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
            }
        }

        List<ProductEntity> relatedProducts = null;
        if (!CollectionUtils.isEmpty(request.getProductRelatedIds())) {
            relatedProducts = productPort.getProductsByIds(request.getProductRelatedIds());

            if (relatedProducts.size() != request.getProductRelatedIds().size()) {
                log.error("[CreateProductUseCase] create product failed, related products not found");
                throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
            }
        }


        // save main product
        ProductEntity product = ProductMapper.INSTANCE.toEntityFromRequest(request);
        product.setIsHasOptions(!CollectionUtils.isEmpty(request.getProductVariants()));
        ProductEntity savedProduct = productPort.save(product);

        savedProduct.setBrand(brand);


        // build product image
        if (!CollectionUtils.isEmpty(request.getImages())) {
            List<ImageEntity> images = request.getImages().stream()
                    .map(image -> ImageEntity.builder()
                            .image(image)
                            .entityType(ImageType.PRODUCT.name())
                            .entityId(savedProduct.getId())
                            .build())
                    .toList();

            savedProduct.setImages(imagePort.saveAll(images));
        }


        // build product category
        if (!CollectionUtils.isEmpty(request.getCategoryIds())) {
            List<ProductCategoryEntity> productCategories = request.getCategoryIds().stream()
                    .map(categoryId -> ProductCategoryEntity.builder()
                            .productId(savedProduct.getId())
                            .categoryId(categoryId)
                            .build())
                    .toList();

            productCategoryPort.saveAll(productCategories);
            savedProduct.setCategories(categories);
        }


        // build product variant
        if (!CollectionUtils.isEmpty(request.getProductVariants())) {
            List<ImageEntity> allVariantImages = new ArrayList<>();

            List<ProductEntity> productVariants = request.getProductVariants().stream()
                    .map(productVariant -> {
                        ProductEntity variant = ProductEntity.builder()
                                .name(productVariant.getName())
                                .slug(productVariant.getSlug())
                                .price(productVariant.getPrice())
                                .thumbnailImage(productVariant.getThumbnailImage())
                                .parentId(savedProduct.getId())
                                .isPublished(savedProduct.getIsPublished())
                                .stockQuantity(productVariant.getStockQuantity())
                                .build();

                        ProductEntity savedVariant = productPort.save(variant);

                        // build image for product variant
                        if (!CollectionUtils.isEmpty(productVariant.getImages())) {
                            List<ImageEntity> variantImages = productVariant.getImages().stream()
                                    .map(image -> ImageEntity.builder()
                                            .image(image)
                                            .entityType(ImageType.PRODUCT.name())
                                            .entityId(savedVariant.getId())
                                            .build())
                                    .toList();

                            allVariantImages.addAll(variantImages);
                        }

                        return savedVariant;
                    })
                    .toList();

            if (!CollectionUtils.isEmpty(allVariantImages)) {
                imagePort.saveAll(allVariantImages);
            }
            savedProduct.setProductVariants(productVariants);
        }


        // build related product
        if (!CollectionUtils.isEmpty(request.getProductRelatedIds())) {
            List<ProductRelatedEntity> productRelatedEntities = request.getProductRelatedIds().stream()
                    .map(relatedProductId -> ProductRelatedEntity.builder()
                            .productId(savedProduct.getId())
                            .relatedProductId(relatedProductId)
                            .build())
                    .toList();

            productRelatedPort.saveAll(productRelatedEntities);
            savedProduct.setRelatedProducts(relatedProducts);
        }


        // send ProductCreatedEvent
        List<ProductCreatedEvent> productCreatedEvents = new ArrayList<>();

        ProductCreatedEvent mainProductCreatedEvent = ProductCreatedEvent.builder()
                .id(savedProduct.getId())
                .name(savedProduct.getName())
                .slug(savedProduct.getSlug())
                .price(savedProduct.getPrice())
                .isPublished(savedProduct.getIsPublished())
                .brand(brand != null ? brand.getName() : null)
                .categories(categories != null ? categories.stream().map(CategoryEntity::getName).toList() : null)
                .build();
        productCreatedEvents.add(mainProductCreatedEvent);

        if (!CollectionUtils.isEmpty(savedProduct.getProductVariants())) {
            productCreatedEvents.addAll(savedProduct.getProductVariants().stream()
                    .map(productVariant -> ProductCreatedEvent.builder()
                            .id(productVariant.getId())
                            .name(productVariant.getName())
                            .slug(productVariant.getSlug())
                            .price(productVariant.getPrice())
                            .isPublished(productVariant.getIsPublished())
                            .brand(mainProductCreatedEvent.getBrand())
                            .categories(mainProductCreatedEvent.getCategories())
                            .build())
                    .toList());
        }

        productCreatedEvents.forEach(productEventPort::publishProductDomainEvent);


        return savedProduct;
    }
}
