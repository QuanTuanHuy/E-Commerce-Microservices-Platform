package hust.project.promotionservice.port;

import hust.project.promotionservice.entity.PromotionUsageEntity;

import java.util.List;

public interface IPromotionUsagePort {
    PromotionUsageEntity save(PromotionUsageEntity promotionUsageEntity);

    List<PromotionUsageEntity> getPromotionUsagesByPromotionId(Long promotionId);
}
