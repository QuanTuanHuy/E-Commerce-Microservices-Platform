package hust.project.productservice.repository;

import hust.project.productservice.model.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends IBaseRepository<ProductModel>, CustomProductRepository {
    List<ProductModel> findByIdIn(List<Long> ids);

    List<ProductModel> findByParentId(Long parentId);

    List<ProductModel> findByBrandId(Long brandId);
}
