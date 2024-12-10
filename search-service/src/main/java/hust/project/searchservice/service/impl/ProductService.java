package hust.project.searchservice.service.impl;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.CreateProductRequest;
import hust.project.searchservice.entity.dto.request.GetProductRequest;
import hust.project.searchservice.entity.dto.request.UpdateProductRequest;
import hust.project.searchservice.entity.dto.response.PageInfo;
import hust.project.searchservice.service.IProductService;
import hust.project.searchservice.usecase.CreateProductUseCase;
import hust.project.searchservice.usecase.DeleteProductUseCase;
import hust.project.searchservice.usecase.GetProductUseCase;
import hust.project.searchservice.usecase.UpdateProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final GetProductUseCase getProductUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @Override
    public ProductEntity createProduct(CreateProductRequest request) {
        return createProductUseCase.createProduct(request);
    }

    @Override
    public ProductEntity updateProduct(Long id, UpdateProductRequest request) {
        return updateProductUseCase.updateProduct(id, request);
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
    public void deleteProduct(Long id) {
        deleteProductUseCase.deleteProduct(id);
    }
}
