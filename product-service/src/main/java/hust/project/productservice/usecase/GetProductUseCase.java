package hust.project.productservice.usecase;

import hust.project.productservice.constants.ImageType;
import hust.project.productservice.entity.*;
import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.port.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetProductUseCase {
    private final IProductPort productPort;
    private final IImagePort imagePort;
    private final ICategoryPort categoryPort;
    private final IProductCategoryPort productCategoryPort;
    private final IBrandPort brandPort;
    private final IProductRelatedPort productRelatedPort;


    public ProductEntity getDetailProduct(Long id) {
        ProductEntity product = productPort.getProductById(id);

        if (product.getBrandId() != null) {
            product.setBrand(brandPort.getBrandById(product.getBrandId()));
        }

        List<Long> categoryIds = productCategoryPort.getProductCategoriesByProductId(id)
                .stream().map(ProductCategoryEntity::getCategoryId)
                .toList();

        if (!CollectionUtils.isEmpty(categoryIds)) {
            product.setCategories(categoryPort.getCategoriesByIds(categoryIds));
        }


        product.setImages(imagePort.getImageByEntityIdAndEntityType(id, ImageType.PRODUCT.name()));


        if (product.getIsHasOptions() != null && product.getIsHasOptions().equals(true)) {
            product.setProductVariants(productPort.getProductsByParentId(id));
        }


        List<Long> relatedProductIds = productRelatedPort.getProductRelatedByProductId(id)
                .stream().map(ProductRelatedEntity::getRelatedProductId)
                .toList();
        if (!CollectionUtils.isEmpty(relatedProductIds)) {
            product.setRelatedProducts(productPort.getProductsByIds(relatedProductIds));
        }

        return product;
    }

    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        var result = productPort.getAllProducts(filter);

        var products = result.getSecond().stream().filter(product -> product.getIsPublished().equals(true)).toList();
        if (CollectionUtils.isEmpty(products)) {
            return Pair.of(result.getFirst(), List.of());
        }


        List<Long> brandIds = products.stream().map(ProductEntity::getBrandId).distinct().toList();
        List<BrandEntity> brands = brandPort.getBrandsByIds(brandIds);
        var mapIdToBrand = brands.stream().collect(Collectors.toMap(BrandEntity::getId, Function.identity()));


        List<Long> productIds = products.stream().map(ProductEntity::getId).toList();


        var mapProductIdToImages = imagePort.getImageByEntityIdsAndEntityType(productIds, ImageType.PRODUCT.name())
                .stream()
                .collect(Collectors.groupingBy(ImageEntity::getEntityId));


        List<ProductCategoryEntity> productCategories = productCategoryPort.getProductCategoriesByProductIds(productIds);

        List<Long> categoryIds = productCategories.stream()
                .map(ProductCategoryEntity::getCategoryId)
                .distinct().toList();
        List<CategoryEntity> categories = categoryPort.getCategoriesByIds(categoryIds);

        var mapIdToCategory = categories.stream().collect(Collectors.toMap(CategoryEntity::getId, Function.identity()));


        products.forEach(product -> {
            if (product.getBrandId() != null) {
                product.setBrand(mapIdToBrand.getOrDefault(product.getBrandId(), null));
            }

            List<Long> currentCategoryIds = productCategories.stream()
                    .filter(productCategory -> productCategory.getProductId().equals(product.getId()))
                    .map(ProductCategoryEntity::getCategoryId)
                    .toList();

            if (!CollectionUtils.isEmpty(currentCategoryIds)) {
                List<CategoryEntity> currentCategory = currentCategoryIds.stream()
                        .map(categoryId -> mapIdToCategory.getOrDefault(categoryId, null))
                        .toList();

                product.setCategories(currentCategory);
            }

            product.setImages(mapProductIdToImages.getOrDefault(product.getId(), List.of()));
        });

        return Pair.of(result.getFirst(), products);
    }

}
