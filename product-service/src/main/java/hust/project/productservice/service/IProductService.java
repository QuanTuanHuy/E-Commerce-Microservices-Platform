package hust.project.productservice.service;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.*;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.entity.dto.response.ProductGetModel;
import hust.project.productservice.entity.dto.response.ProductThumbnailResponse;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProductService {
    ProductEntity createProduct(CreateProductRequest request);

    ProductEntity getDetailProduct(Long id);

    Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter);

    List<ProductThumbnailResponse> getAllProductThumbnails(GetProductListRequest filter);

    List<ProductGetModel> getProductsByIds(List<Long> ids);

    List<ProductGetModel> getProductsByCategoryIds(List<Long> categoryIds);

    List<ProductGetModel> getProductByBrandIds(List<Long> brandIds);

    List<ProductGetModel> getProductVariants(Long parentId);

    ProductEntity updateProduct(Long id, UpdateProductRequest request);

    void updateProductQuantity(List<UpdateProductQuantityRequest> requests);

    void deleteProduct(Long id);
}
