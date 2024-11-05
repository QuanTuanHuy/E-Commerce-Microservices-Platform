package hust.project.productservice.service;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.CreateProductRequest;
import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.request.GetProductListRequest;
import hust.project.productservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.entity.dto.response.ProductThumbnailResponse;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductService {
    ProductEntity createProduct(CreateProductRequest request);

    ProductEntity getDetailProduct(Long id);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

    List<ProductThumbnailResponse> getAllProductThumbnails(GetProductListRequest filter);

    void updateProductQuantity(List<UpdateProductQuantityRequest> requests);

    void deleteProduct(Long id);
}
