package hust.project.productservice.service;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.CreateProductRequest;
import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductService {
    ProductEntity createProduct(CreateProductRequest request);

    ProductEntity getDetailProduct(Long id);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

    void deleteProduct(Long id);
}
