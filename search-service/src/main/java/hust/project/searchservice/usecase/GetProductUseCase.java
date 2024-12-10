package hust.project.searchservice.usecase;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.GetProductRequest;
import hust.project.searchservice.entity.dto.response.PageInfo;
import hust.project.searchservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductUseCase {
    private final IProductPort productPort;

    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        return productPort.getAllProducts(filter);
    }

    public ProductEntity getDetailProduct(Long id) {
        return productPort.getProductById(id);
    }
}
