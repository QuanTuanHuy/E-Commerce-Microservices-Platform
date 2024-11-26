package hust.project.promotionservice.service;

import com.nimbusds.jose.util.Pair;
import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.*;
import hust.project.promotionservice.entity.dto.response.PageInfo;
import hust.project.promotionservice.entity.dto.response.PromotionDetailModel;
import hust.project.promotionservice.entity.dto.response.VerifyPromotionResult;

import java.util.List;

public interface IPromotionService {
    PromotionEntity createPromotion(CreatePromotionRequest request);

    Pair<PageInfo, List<PromotionDetailModel>> getAllPromotions(GetPromotionRequest filter);

    PromotionDetailModel getDetailPromotion(Long id);

    VerifyPromotionResult verifyPromotion(VerifyPromotionRequest request);

    PromotionEntity updatePromotion(Long id, UpdatePromotionRequest request);

    void updatePromotionUsage(Long promotionId, UpdatePromotionUsageRequest request);

    void deletePromotion(Long id);
}
