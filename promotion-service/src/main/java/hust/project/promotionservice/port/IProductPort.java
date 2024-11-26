package hust.project.promotionservice.port;

import hust.project.promotionservice.entity.dto.response.ProductGetModel;

import java.util.List;

public interface IProductPort {
    List<ProductGetModel> getProductsByIds(List<Long> ids);

    List<ProductGetModel> getProductsByBrandIds(List<Long> brandIds);

    List<ProductGetModel> getProductsByCategoryIds(List<Long> categoryIds);
}
