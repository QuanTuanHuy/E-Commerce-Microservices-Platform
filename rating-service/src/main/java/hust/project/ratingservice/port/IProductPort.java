package hust.project.ratingservice.port;

import hust.project.ratingservice.entity.dto.response.ProductGetModel;

import java.util.List;

public interface IProductPort {
    List<ProductGetModel> getProductVariants(Long parentId);
}
