package hust.project.productservice.repository;

import hust.project.productservice.model.ProductRelatedModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRelatedRepository extends IBaseRepository<ProductRelatedModel> {
    List<ProductRelatedModel> findByProductId(Long productId);

    void deleteByIdIn(List<Long> ids);

    void deleteByProductId(Long productId);
}
