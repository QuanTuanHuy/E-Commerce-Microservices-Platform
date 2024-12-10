package hust.project.searchservice.usecase;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.UpdateProductRequest;
import hust.project.searchservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductUseCase {
    private final IProductPort productPort;

    public ProductEntity updateProduct(Long id, UpdateProductRequest request) {
        ProductEntity product = productPort.getProductById(id);

        product.setName(request.getName());
        product.setSlug(request.getSlug());
        product.setBrand(request.getBrand());
        product.setCategories(request.getCategories());
        product.setPrice(request.getPrice());
        product.setIsPublished(request.getIsPublished());

        return productPort.save(product);
    }
}
