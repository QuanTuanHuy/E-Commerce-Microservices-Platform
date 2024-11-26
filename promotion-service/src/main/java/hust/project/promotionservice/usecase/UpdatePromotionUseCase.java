package hust.project.promotionservice.usecase;

import hust.project.promotionservice.constant.ApplyTo;
import hust.project.promotionservice.constant.DiscountType;
import hust.project.promotionservice.constant.ErrorCode;
import hust.project.promotionservice.constant.UsageType;
import hust.project.promotionservice.entity.PromotionApplyEntity;
import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.PromotionUsageEntity;
import hust.project.promotionservice.entity.dto.request.UpdatePromotionUsageRequest;
import hust.project.promotionservice.entity.dto.request.UpdatePromotionRequest;
import hust.project.promotionservice.exception.AppException;
import hust.project.promotionservice.port.IPromotionApplyPort;
import hust.project.promotionservice.port.IPromotionPort;
import hust.project.promotionservice.port.IPromotionUsagePort;
import hust.project.promotionservice.utils.AuthenticationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UpdatePromotionUseCase {
    private final IPromotionPort promotionPort;
    private final IPromotionUsagePort promotionUsagePort;
    private final IPromotionApplyPort promotionApplyPort;


    public PromotionEntity updatePromotion(Long id, UpdatePromotionRequest request) {
        PromotionEntity promotion = promotionPort.getPromotionById(id);

        if (promotion == null) {
            log.error("[UpdatePromotionUseCase]: promotion not found, id: {}", id);
            throw new AppException(ErrorCode.UPDATE_PROMOTION_FAILED);
        }

        if (request.getEndDate().toInstant().isBefore(request.getStartDate().toInstant())) {
            log.error("[CreatePromotionUseCase]: End date must be after start date");
            throw new AppException(ErrorCode.CREATE_PROMOTION_FAILED);
        }

        promotionApplyPort.deletePromotionAppliesByPromotionId(id);


        promotion.setName(request.getName());
        promotion.setSlug(request.getSlug());
        promotion.setCouponCode(request.getCouponCode());
        promotion.setDescription(request.getDescription());
        promotion.setApplyTo(ApplyTo.valueOf(request.getApplyTo()).name());
        promotion.setUsageType(UsageType.valueOf(request.getUsageType()).name());
        promotion.setDiscountType(DiscountType.valueOf(request.getDiscountType()).name());
        promotion.setUsageLimit(request.getUsageLimit());
        promotion.setDiscountPercentage(request.getDiscountPercentage());
        promotion.setDiscountAmount(request.getDiscountAmount());
        promotion.setMinimumOrderAmount(request.getMinimumOrderAmount());
        promotion.setIsActive(request.getIsActive());
        promotion.setStartDate(request.getStartDate().toInstant());
        promotion.setEndDate(request.getEndDate().toInstant());

        promotion = promotionPort.save(promotion);
        List<PromotionApplyEntity> newPromotionApplies = promotion.createPromotionApplies(request);
        if (!CollectionUtils.isEmpty(newPromotionApplies)) {
            promotion.setPromotionApplies(promotionApplyPort.saveAll(newPromotionApplies));
        }

        return promotion;
    }

    public void updatePromotionUsage(Long promotionId,
                                     UpdatePromotionUsageRequest request) {
        PromotionEntity promotion = promotionPort.getPromotionById(promotionId);
        if (promotion == null) {
            log.error("[UpdatePromotionUseCase]: promotion not found, id: {}", promotionId);
            throw new AppException(ErrorCode.UPDATE_PROMOTION_FAILED);
        }

        promotion.setUsageCount(promotion.getUsageCount() + 1);
        promotionPort.save(promotion);


        PromotionUsageEntity promotionUsage = PromotionUsageEntity.builder()
                .orderId(request.getOrderId())
                .productId(request.getProductId())
                .promotionId(promotionId)
                .userId(AuthenticationUtils.getCurrentUserId())
                .build();

        promotionUsagePort.save(promotionUsage);
    }

}
