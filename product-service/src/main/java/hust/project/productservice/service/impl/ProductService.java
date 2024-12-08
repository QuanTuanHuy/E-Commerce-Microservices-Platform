package hust.project.productservice.service.impl;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.*;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.entity.dto.response.ProductGetModel;
import hust.project.productservice.entity.dto.response.ProductThumbnailResponse;
import hust.project.productservice.service.IProductService;
import hust.project.productservice.usecase.CreateProductUseCase;
import hust.project.productservice.usecase.DeleteProductUseCase;
import hust.project.productservice.usecase.GetProductUseCase;
import hust.project.productservice.usecase.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final CreateProductUseCase createProductUseCase;
    private final GetProductUseCase getProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    @Override
    public ProductEntity createProduct(CreateProductRequest request) {
        return createProductUseCase.createProduct(request);
    }

    @Override
    public ProductEntity getDetailProduct(Long id) {
        return getProductUseCase.getDetailProduct(id);
    }

    @Override
    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        return getProductUseCase.getAllProducts(filter);
    }

    @Override
    public List<ProductThumbnailResponse> getAllProductThumbnails(GetProductListRequest filter) {
        return getProductUseCase.getAllProductThumbnails(filter);
    }

    @Override
    public List<ProductGetModel> getProductsByIds(List<Long> ids) {
        return getProductUseCase.getProductsByIds(ids);
    }

    @Override
    public List<ProductGetModel> getProductsByCategoryIds(List<Long> categoryIds) {
        return getProductUseCase.getProductsByCategoryIds(categoryIds);
    }

    @Override
    public List<ProductGetModel> getProductByBrandIds(List<Long> brandIds) {
        return getProductUseCase.getProductsByBrandIds(brandIds);
    }

    @Override
    public List<ProductGetModel> getProductVariants(Long parentId) {
        return getProductUseCase.getProductVariants(parentId);
    }

    @Override
    public ProductEntity updateProduct(Long id, UpdateProductRequest request) {
        return updateProductUseCase.updateProduct(id, request);
    }

    @Override
    public void updateProductQuantity(List<UpdateProductQuantityRequest> requests) {
        updateProductUseCase.updateProductQuantity(requests);
    }

    @Override
    public void deleteProduct(Long id) {
        deleteProductUseCase.deleteProduct(id);
    }
}
