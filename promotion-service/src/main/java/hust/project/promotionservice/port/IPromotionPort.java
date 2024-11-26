package hust.project.promotionservice.port;

import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.GetPromotionRequest;
import hust.project.promotionservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IPromotionPort {
    PromotionEntity save(PromotionEntity promotionEntity);

    PromotionEntity getPromotionById(Long id);

    PromotionEntity getPromotionByCouponCode(String couponCode);

    PromotionEntity getPromotionBySlug(String slug);

    Pair<PageInfo, List<PromotionEntity>> getAllPromotions(GetPromotionRequest filter);

    void deletePromotion(Long id);
}
