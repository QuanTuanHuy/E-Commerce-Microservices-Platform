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
    public void createProduct(ProductEntity productEntity) {
        productPort.save(productEntity);
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return productPort.getProductById(id);
    }

    @Override
    public void updateProduct(ProductEntity productEntity) {
        ProductEntity product = productPort.getProductById(productEntity.getId());
        product.setName(productEntity.getName());
        product.setSlug(productEntity.getSlug());
        product.setPrice(productEntity.getPrice());
        product.setId(productEntity.getId());

        productPort.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductEntity product = productPort.getProductById(id);
        product.setIsPublished(false);
        productPort.save(product);
    }
}
