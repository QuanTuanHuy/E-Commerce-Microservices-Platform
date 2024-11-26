package hust.project.promotionservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.promotionservice.entity.dto.response.BrandGetModel;
import hust.project.promotionservice.port.IBrandPort;
import hust.project.promotionservice.repository.httpClient.IProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandAdapter implements IBrandPort {
    private final IProductClient productClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<BrandGetModel> getBrandsByIds(List<Long> ids) {
        var response = productClient.getBrandsByIds(ids).getBody().getData();

        if (response == null) {
            return List.of();
        }

        return objectMapper.convertValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, BrandGetModel.class));
    }
}
