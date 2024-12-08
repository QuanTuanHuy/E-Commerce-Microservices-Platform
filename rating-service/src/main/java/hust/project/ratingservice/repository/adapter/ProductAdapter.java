package hust.project.ratingservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.ratingservice.entity.dto.response.ProductGetModel;
import hust.project.ratingservice.port.IProductPort;
import hust.project.ratingservice.repository.httpclient.IProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements IProductPort {
    private final IProductClient productClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<ProductGetModel> getProductVariants(Long parentId) {
        var response = productClient.getProductVariants(parentId).getBody().getData();

        if (response == null) {
            return null;
        }

        return objectMapper.convertValue(response, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, ProductGetModel.class));
    }
}
