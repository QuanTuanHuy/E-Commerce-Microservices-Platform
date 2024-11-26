package hust.project.promotionservice.port;

import hust.project.promotionservice.entity.dto.response.BrandGetModel;

import java.util.List;

public interface IBrandPort {
    List<BrandGetModel> getBrandsByIds(List<Long> ids);
}
