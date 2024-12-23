package hust.project.orderservice.service.impl;

import hust.project.orderservice.entity.ProductEntity;
import hust.project.orderservice.port.IProductPort;
import hust.project.orderservice.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final IProductPort productPort;

    @Override
    public ProductEntity createProduct(ProductEntity productEntity) {
        return productPort.save(productEntity);
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productPort.getProductById(id);
    }

    @Override
    public ProductEntity updateProduct(ProductEntity productEntity) {
        ProductEntity product = productPort.getProductById(productEntity.getId());
        product.setName(productEntity.getName());
        product.setSlug(productEntity.getSlug());
        product.setPrice(productEntity.getPrice());
        product.setId(productEntity.getId());

        return productPort.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productPort.deleteProductById(id);
    }
}
