package hust.project.cartservice.usecase;

import hust.project.cartservice.entity.dto.response.ProductThumbnailResponse;
import hust.project.cartservice.repository.httpclient.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProductUseCase {
    private final ProductClient productClient;

    public List<ProductThumbnailResponse> getProductsByIds(List<Long> productIds) {
        return (List<ProductThumbnailResponse>) productClient
                .getAllProductThumbnails(productIds)
                .getBody().getData();
    }
}
