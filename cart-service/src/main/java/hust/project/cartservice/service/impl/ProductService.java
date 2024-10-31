package hust.project.cartservice.service.impl;

import hust.project.cartservice.entity.dto.response.ProductThumbnailResponse;
import hust.project.cartservice.service.IProductService;
import hust.project.cartservice.usecase.GetProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
    private final GetProductUseCase getProductUseCase;

    @Override
    public List<ProductThumbnailResponse> getProductsByIds(List<Long> productIds) {
        return getProductUseCase.getProductsByIds(productIds);
    }
}
