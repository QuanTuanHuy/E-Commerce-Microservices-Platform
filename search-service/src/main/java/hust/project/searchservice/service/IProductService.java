package hust.project.searchservice.service;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.CreateProductRequest;
import hust.project.searchservice.entity.dto.request.GetProductRequest;
import hust.project.searchservice.entity.dto.request.UpdateProductRequest;
import hust.project.searchservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductService {
    ProductEntity createProduct(CreateProductRequest request);

    ProductEntity updateProduct(Long id, UpdateProductRequest request);

    ProductEntity getDetailProduct(Long id);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

    void deleteProduct(Long id);
}
