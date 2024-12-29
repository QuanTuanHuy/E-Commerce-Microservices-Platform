package hust.project.productservice.usecase;

import hust.project.common.event.ProductUpdatedEvent;
import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.constants.ImageType;
import hust.project.productservice.entity.*;
import hust.project.productservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.productservice.entity.dto.request.UpdateProductRequest;
import hust.project.productservice.entity.dto.request.UpdateProductVariationRequest;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.port.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UpdateProductUseCase {
    private final IProductPort productPort;
    private final IBrandPort brandPort;
    private final ICategoryPort categoryPort;
    private final IProductCategoryPort productCategoryPort;
    private final IProductRelatedPort productRelatedPort;
    private final IImagePort imagePort;
    private final IProductEventPort productEventPort;

    public ProductEntity updateProduct(Long id, UpdateProductRequest request) {
        ProductEntity product = productPort.getProductById(id);

        // check brand, category, related product exist
        BrandEntity brand = null;
        if (request.getBrandId() != null) {
            brand = brandPort.getBrandById(request.getBrandId());
        }


        List<CategoryEntity> categories = null;
        if (!CollectionUtils.isEmpty(request.getCategoryIds())) {
            categories = categoryPort.getCategoriesByIds(request.getCategoryIds().stream().toList());

            if (categories.size() != request.getCategoryIds().size()) {
                log.error("[CreateProductUseCase] create product failed, categories not found");
                throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
            }
        }

        List<ProductEntity> relatedProducts;
        if (!CollectionUtils.isEmpty(request.getRelatedProductIds())) {
            relatedProducts = productPort.getProductsByIds(request.getRelatedProductIds().stream().toList());

            if (relatedProducts.size() != request.getRelatedProductIds().size()) {
                log.error("[CreateProductUseCase] create product failed, related products not found");
                throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
            }
        }

        product.setName(request.getName());
        product.setSlug(request.getSlug());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setIsPublished(request.getIsPublished());
        product.setBrandId(request.getBrandId());
        product.setThumbnailImage(request.getThumbnailImage());

        product = productPort.save(product);
        final Boolean isPublished = product.getIsPublished();


        // update product category
        List<ProductCategoryEntity> existedProductCategories = productCategoryPort.getProductCategoriesByProductId(id);
        Set<Long> existedCategoryIds = existedProductCategories.stream()
                .map(ProductCategoryEntity::getCategoryId)
                .collect(Collectors.toSet());

        List<Long> newCategoryIds = request.getCategoryIds().stream()
                .filter(categoryId -> !existedCategoryIds.contains(categoryId))
                .toList();

        Set<Long> deleteCategoryIds = existedCategoryIds.stream()
                .filter(categoryId -> !request.getCategoryIds().contains(categoryId))
                .collect(Collectors.toSet());

        if (!CollectionUtils.isEmpty(newCategoryIds)) {
            List<ProductCategoryEntity> newProductCategories = newCategoryIds.stream()
                    .map(categoryId -> ProductCategoryEntity.builder()
                            .productId(id)
                            .categoryId(categoryId)
                            .build())
                    .toList();

            productCategoryPort.saveAll(newProductCategories);
        }

        if (!CollectionUtils.isEmpty(deleteCategoryIds)) {
            List<Long> deleteProductCategoryIds = existedProductCategories.stream()
                    .filter(pc -> deleteCategoryIds.contains(pc.getCategoryId()))
                    .map(ProductCategoryEntity::getId)
                    .toList();

            productCategoryPort.deleteProductCategoriesByIds(deleteProductCategoryIds);
        }


        // update product images
        List<Long> allDeleteImageIds = new ArrayList<>();
        List<ImageEntity> allNewImageEntities = new ArrayList<>();


        setImageForProduct(id, request.getImages(), allNewImageEntities, allDeleteImageIds);



        // update product related
        if (CollectionUtils.isEmpty(request.getRelatedProductIds())) {
            productRelatedPort.deleteProductRelatedByProductId(id);
        } else {
            List<ProductRelatedEntity> existedProductRelated = productRelatedPort.getProductRelatedByProductId(id);
            Set<Long> existedRelatedProductIds = existedProductRelated.stream()
                    .map(ProductRelatedEntity::getRelatedProductId)
                    .collect(Collectors.toSet());

            List<Long> newRelatedProductIds = request.getRelatedProductIds().stream()
                    .filter(relatedProductId -> !existedRelatedProductIds.contains(relatedProductId)).toList();


            List<Long> deleteProductRelatedIds = existedProductRelated.stream()
                    .filter(pr -> !request.getRelatedProductIds().contains(pr.getRelatedProductId()))
                    .map(ProductRelatedEntity::getId).toList();

            if (!CollectionUtils.isEmpty(newRelatedProductIds)) {
                List<ProductRelatedEntity> newProductRelated = newRelatedProductIds.stream()
                        .map(relatedProductId -> ProductRelatedEntity.builder()
                                .productId(id)
                                .relatedProductId(relatedProductId)
                                .build())
                        .toList();

                productRelatedPort.saveAll(newProductRelated);
            }

            if (!CollectionUtils.isEmpty(deleteProductRelatedIds)) {
                productRelatedPort.deleteProductRelatedByIds(deleteProductRelatedIds);
            }
        }


        // update existed product variant
        // TODO: update product variant publish status
        List<UpdateProductVariationRequest> updateProductVariantRequests = request.getProductVariants().stream()
                .filter(pv -> pv.getId() != null).toList();

        List<ProductEntity> existedProductVariants = productPort.getProductsByParentId(id);

        updateProductVariantRequests.forEach(updateRequest -> {
            ProductEntity currentProductVariant = existedProductVariants.stream()
                    .filter(p -> p.getId().equals(updateRequest.getId()))
                    .findFirst().orElse(null);

            if (currentProductVariant == null) {
                log.error("[UpdateProductUseCase] update product variant failed, product variant not found");
                throw new AppException(ErrorCode.UPDATE_PRODUCT_FAILED);
            }

            currentProductVariant.setName(updateRequest.getName());
            currentProductVariant.setSlug(updateRequest.getSlug());
            currentProductVariant.setPrice(updateRequest.getPrice());
            currentProductVariant.setIsPublished(isPublished);
            currentProductVariant.setThumbnailImage(updateRequest.getThumbnailImage());


            setImageForProduct(currentProductVariant.getId(), new HashSet<>(updateRequest.getImages()), allNewImageEntities, allDeleteImageIds);

        });

        productPort.saveAll(existedProductVariants);


        // create new product variant
        List<UpdateProductVariationRequest> newProductVariantRequest = request.getProductVariants().stream()
                        .filter(pv -> pv.getId() == null).toList();

        List<ProductEntity> newProductVariants = newProductVariantRequest.stream()
                        .map(productVariantRequest -> ProductEntity.builder()
                                .parentId(id)
                                .name(productVariantRequest.getName())
                                .slug(productVariantRequest.getSlug())
                                .price(productVariantRequest.getPrice())
                                .isPublished(isPublished)
                                .thumbnailImage(productVariantRequest.getThumbnailImage())
                                .build())
                        .toList();

        newProductVariants = productPort.saveAll(newProductVariants);

        newProductVariants.forEach(newProductVariant -> {
            UpdateProductVariationRequest currentRequest = newProductVariantRequest.stream()
                    .filter(pv -> pv.getSlug().equals(newProductVariant.getSlug()))
                    .findFirst().orElse(null);

            if (currentRequest != null) {
                List<ImageEntity> currentImages = currentRequest.getImages().stream()
                        .map(image -> ImageEntity.builder()
                                .entityId(newProductVariant.getId())
                                .entityType(ImageType.PRODUCT.name())
                                .image(image)
                                .build())
                        .toList();

                allNewImageEntities.addAll(currentImages);
            }
        });


        imagePort.saveAll(allNewImageEntities);
        imagePort.deleteImagesByIds(allDeleteImageIds);


        product.setBrand(brand);
        product.setCategories(categories);
        product.setProductVariants(productPort.getProductsByParentId(id));


        // send ProductUpdatedEvent
        List<ProductUpdatedEvent> productUpdatedEvents = new ArrayList<>();

        ProductUpdatedEvent mainProductUpdatedEvent = ProductUpdatedEvent.builder()
                .id(product.getId())
                .name(product.getName())
                .slug(product.getSlug())
                .price(product.getPrice())
                .isPublished(product.getIsPublished())
                .brand(brand != null ? brand.getName() : null)
                .categories(categories != null ? categories.stream().map(CategoryEntity::getName).toList() : null)
                .build();
        productUpdatedEvents.add(mainProductUpdatedEvent);


        List<ProductUpdatedEvent> productVariantUpdatedEvents = product.getProductVariants().stream()
                .map(productVariant -> ProductUpdatedEvent.builder()
                        .id(productVariant.getId())
                        .name(productVariant.getName())
                        .slug(productVariant.getSlug())
                        .price(productVariant.getPrice())
                        .isPublished(productVariant.getIsPublished())
                        .brand(mainProductUpdatedEvent.getBrand())
                        .categories(mainProductUpdatedEvent.getCategories())
                        .build())
                .toList();
        productUpdatedEvents.addAll(productVariantUpdatedEvents);

        productUpdatedEvents.forEach(productEventPort::publishProductDomainEvent);


        return product;
    }


    private void setImageForProduct(Long productId, Set<String> requestImageUrls, List<ImageEntity> allNewImageEntities, List<Long> allDeleteImageIds) {
        List<ImageEntity> existedImages = imagePort.getImageByEntityIdAndEntityType(productId, ImageType.PRODUCT.name());
        Set<String> existedImageUrls = existedImages.stream()
                .map(ImageEntity::getImage)
                .collect(Collectors.toSet());

        List<String> newImageUrls = requestImageUrls.stream()
                .filter(image -> !existedImageUrls.contains(image)).toList();

        List<Long> deleteImageIds = existedImages.stream()
                .filter(image -> !requestImageUrls.contains(image.getImage()))
                .map(ImageEntity::getId)
                .toList();

        if (!CollectionUtils.isEmpty(newImageUrls)) {
            List<ImageEntity> newImages = newImageUrls.stream()
                    .map(image -> ImageEntity.builder()
                            .entityId(productId)
                            .entityType(ImageType.PRODUCT.name())
                            .image(image)
                            .build())
                    .toList();

            allNewImageEntities.addAll(newImages);
        }

        if (!CollectionUtils.isEmpty(deleteImageIds)) {
            allDeleteImageIds.addAll(deleteImageIds);
        }
    }

    public void updateProductQuantity(List<UpdateProductQuantityRequest> requests) {
        List<Long> productIds = requests.stream()
                .map(UpdateProductQuantityRequest::getProductId)
                .distinct().toList();

        List<ProductEntity> products = productPort.getProductsByIds(productIds);

        var mapIdToProduct = products.stream()
                .collect(Collectors.toMap(ProductEntity::getId, Function.identity()));


        requests.forEach(request -> {
            ProductEntity product = mapIdToProduct.get(request.getProductId());
            long newQuantity = product.getStockQuantity() + request.getQuantity();
            if (newQuantity < 0) {
                log.error("[UpdateProductUseCase] not enough quantity for product: {}", product.getId());
                throw new AppException(ErrorCode.UPDATE_PRODUCT_QUANTITY_FAILED);
            }
            product.setStockQuantity(newQuantity);
        });


        productPort.saveAll(products);
    }
}
