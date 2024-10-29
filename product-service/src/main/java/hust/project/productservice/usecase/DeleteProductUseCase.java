package hust.project.productservice.usecase;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final IProductPort productPort;

    @Transactional
    public void deleteProduct(Long id) {
        ProductEntity product = productPort.getProductById(id);
        product.setIsPublished(false);

        productPort.save(product);
    }
}
