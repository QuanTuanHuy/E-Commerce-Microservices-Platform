package hust.project.promotionservice.repository;

import hust.project.promotionservice.model.PromotionUsageModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPromotionUsageRepository extends IBaseRepository<PromotionUsageModel> {
    List<PromotionUsageModel> findByPromotionId(Long promotionId);
}
