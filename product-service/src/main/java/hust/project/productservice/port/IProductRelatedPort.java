package hust.project.productservice.port;

import hust.project.productservice.entity.ProductRelatedEntity;

import java.util.List;

public interface IProductRelatedPort {
    List<ProductRelatedEntity> saveAll(List<ProductRelatedEntity> productRelatedEntities);

    List<ProductRelatedEntity> getProductRelatedByProductId(Long productId);

    void deleteProductRelatedByIds(List<Long> ids);

    void deleteProductRelatedByProductId(Long productId);
}
