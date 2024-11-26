package hust.project.promotionservice.port;

import hust.project.promotionservice.entity.dto.response.CategoryGetModel;

import java.util.List;

public interface ICategoryPort {
    List<CategoryGetModel> getCategoryByIds(List<Long> ids);
}
