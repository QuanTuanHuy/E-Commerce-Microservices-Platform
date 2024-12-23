package hust.project.orderservice.service;

import hust.project.orderservice.entity.ProductEntity;

public interface IProductService {
    ProductEntity createProduct(ProductEntity productEntity);

    ProductEntity getProductById(Long id);

    ProductEntity updateProduct(ProductEntity productEntity);

    void deleteProduct(Long id);
}
