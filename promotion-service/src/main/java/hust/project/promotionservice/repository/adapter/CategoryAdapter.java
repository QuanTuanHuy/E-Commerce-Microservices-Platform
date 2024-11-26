package hust.project.promotionservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.promotionservice.entity.dto.response.CategoryGetModel;
import hust.project.promotionservice.port.ICategoryPort;
import hust.project.promotionservice.repository.httpClient.IProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPort {
    private final IProductClient productClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<CategoryGetModel> getCategoryByIds(List<Long> ids) {
        var response = productClient.getCategoriesByIds(ids).getBody().getData();

        if (response == null) {
            return List.of();
        }

        return objectMapper.convertValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, CategoryGetModel.class));
    }
}
