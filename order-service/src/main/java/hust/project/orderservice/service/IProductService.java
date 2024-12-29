package hust.project.orderservice.service;

import hust.project.orderservice.entity.ProductEntity;

public interface IProductService {
    void createProduct(ProductEntity productEntity);

    ProductEntity getProductById(Long id);

    void updateProduct(ProductEntity productEntity);

    void deleteProduct(Long id);
}
