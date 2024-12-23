package hust.project.orderservice.port;

import hust.project.orderservice.entity.ProductEntity;
import hust.project.orderservice.entity.dto.request.UpdateProductQuantityRequest;

import java.util.List;

public interface IProductPort {
    void updateProductQuantity(List<UpdateProductQuantityRequest> requests);

    ProductEntity save(ProductEntity productEntity);

    ProductEntity getProductById(Long id);

    List<ProductEntity> getProductByIds(List<Long> ids);

    void deleteProductById(Long id);
}
