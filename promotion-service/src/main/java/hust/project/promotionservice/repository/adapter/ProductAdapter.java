package hust.project.promotionservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.promotionservice.entity.dto.response.ProductGetModel;
import hust.project.promotionservice.port.IProductPort;
import hust.project.promotionservice.repository.httpClient.IProductClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements IProductPort {
    private final IProductClient productClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<ProductGetModel> getProductsByIds(List<Long> ids) {
        var response = productClient.getProductsByIds(ids).getBody().getData();

        if (response == null) {
            return List.of();
        }

        return convertResponseToProductGetModelList(response);
    }

    @Override
    public List<ProductGetModel> getProductsByBrandIds(List<Long> brandIds) {
        var response = productClient.getProductsByBrandIds(brandIds).getBody().getData();

        if (response == null) {
            return List.of();
        }

        return convertResponseToProductGetModelList(response);
    }

    @Override
    public List<ProductGetModel> getProductsByCategoryIds(List<Long> categoryIds) {
        var response = productClient.getProductsByCategoryIds(categoryIds).getBody().getData();

        if (response == null) {
            return List.of();
        }

        return convertResponseToProductGetModelList(response);
    }

    private List<ProductGetModel> convertResponseToProductGetModelList(Object response) {
        return objectMapper.convertValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ProductGetModel.class));
    }
}
