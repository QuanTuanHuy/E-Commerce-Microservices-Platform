package hust.project.productservice.repository;

import hust.project.productservice.model.ProductCategoryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductCategoryRepository extends IBaseRepository<ProductCategoryModel> {
    List<ProductCategoryModel> findByProductId(Long productId);

    List<ProductCategoryModel> findByProductIdIn(List<Long> productIds);

    List<ProductCategoryModel> findByCategoryId(Long categoryId);

    List<ProductCategoryModel> findByCategoryIdIn(List<Long> categoryIds);

    void deleteByIdIn(List<Long> ids);
}
