package hust.project.productservice.service.impl;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.CreateProductRequest;
import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.service.IProductService;
import hust.project.productservice.usecase.CreateProductUseCase;
import hust.project.productservice.usecase.DeleteProductUseCase;
import hust.project.productservice.usecase.GetProductUseCase;
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
    public void deleteProduct(Long id) {
        deleteProductUseCase.deleteProduct(id);
    }
}