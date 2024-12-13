package hust.project.searchservice.usecase;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductUseCase {
    private final IProductPort productPort;

    public void deleteProduct(Long id) {
        ProductEntity product = productPort.getProductById(id);
        product.setIsPublished(false);
        productPort.save(product);
    }
}
