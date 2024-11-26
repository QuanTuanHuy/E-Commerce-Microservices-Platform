package hust.project.productservice.port;

import hust.project.productservice.entity.ProductCategoryEntity;

import java.util.List;

public interface IProductCategoryPort {
    List<ProductCategoryEntity> saveAll(List<ProductCategoryEntity> productCategoryEntities);

    List<ProductCategoryEntity> getProductCategoriesByProductId(Long productId);

    List<ProductCategoryEntity> getProductCategoriesByProductIds(List<Long> productIds);

    List<ProductCategoryEntity> getProductCategoriesByCategoryId(Long categoryId);

    List<ProductCategoryEntity> getProductCategoriesByCategoriesIds(List<Long> categoryIds);

    void deleteProductCategoriesByIds(List<Long> ids);
}
