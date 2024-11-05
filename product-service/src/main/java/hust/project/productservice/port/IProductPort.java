package hust.project.productservice.port;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.request.GetProductListRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductPort {
    ProductEntity save(ProductEntity productEntity);

    List<ProductEntity> saveAll(List<ProductEntity> productEntities);

    ProductEntity getProductById(Long id);

    List<ProductEntity> getProductsByIds(List<Long> ids);

    List<ProductEntity> getAllProducts(GetProductListRequest filter);

    List<ProductEntity> getProductsByParentId(Long parentId);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

}
