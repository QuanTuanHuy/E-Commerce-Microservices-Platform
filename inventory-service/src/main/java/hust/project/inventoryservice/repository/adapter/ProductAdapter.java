package hust.project.inventoryservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.inventoryservice.entity.dto.request.GetProductListRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.response.ProductThumbnailResponse;
import hust.project.inventoryservice.port.IProductPort;
import hust.project.inventoryservice.repository.httpclient.IProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements IProductPort {
    private final IProductClient productClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<ProductThumbnailResponse> getProductList(GetProductListRequest request) {
        var response = productClient.getProductList(
                request.getProductIds(),
                request.getSlug(),
                request.getName()
        ).getBody().getData();

        if (response == null) {
            return List.of();
        }

        return objectMapper.convertValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductThumbnailResponse.class));

    }

    @Override
    public void updateProductQuantity(List<UpdateProductQuantityRequest> requests) {
        productClient.updateProductQuantity(requests);
    }
}
