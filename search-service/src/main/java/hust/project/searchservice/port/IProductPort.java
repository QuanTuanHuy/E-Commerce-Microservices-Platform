package hust.project.searchservice.port;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.GetProductRequest;
import hust.project.searchservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductPort {
    ProductEntity save(ProductEntity productEntity);

    ProductEntity getProductById(Long id);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

    void deleteProductById(Long id);
}
