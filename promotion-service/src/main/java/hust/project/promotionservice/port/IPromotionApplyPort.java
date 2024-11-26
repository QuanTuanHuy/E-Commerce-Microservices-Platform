package hust.project.promotionservice.port;

import hust.project.promotionservice.entity.PromotionApplyEntity;

import java.util.List;

public interface IPromotionApplyPort {
    List<PromotionApplyEntity> saveAll(List<PromotionApplyEntity> promotionApplyEntities);

    List<PromotionApplyEntity> getPromotionAppliesByPromotionId(Long promotionId);

    List<PromotionApplyEntity> getPromotionAppliesByPromotionIds(List<Long> promotionIds);

    void deletePromotionAppliesByPromotionId(Long promotionId);
}
