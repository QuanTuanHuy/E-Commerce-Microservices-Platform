package hust.project.promotionservice.repository;

import hust.project.promotionservice.model.PromotionApplyModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPromotionApplyRepository extends IBaseRepository<PromotionApplyModel> {
    List<PromotionApplyModel> findByPromotionId(Long promotionId);

    List<PromotionApplyModel> findByPromotionIdIn(List<Long> promotionIds);

    void deleteByPromotionId(Long promotionId);
}
