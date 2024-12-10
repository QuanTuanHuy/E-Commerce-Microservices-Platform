package hust.project.searchservice.usecase;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.CreateProductRequest;
import hust.project.searchservice.mapper.ProductMapper;
import hust.project.searchservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CreateProductUseCase {
    private final IProductPort productPort;

    public ProductEntity createProduct(CreateProductRequest request) {
        ProductEntity product = ProductMapper.INSTANCE.toEntityFromRequest(request);
        product.setCreatedAt(Instant.now());

        return productPort.save(product);
    }
}
