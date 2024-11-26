package hust.project.promotionservice.repository;

import hust.project.promotionservice.model.PromotionModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPromotionRepository extends IBaseRepository<PromotionModel> {
    Optional<PromotionModel> findByCouponCode(String couponCode);

    Optional<PromotionModel> findBySlug(String slug);
}
