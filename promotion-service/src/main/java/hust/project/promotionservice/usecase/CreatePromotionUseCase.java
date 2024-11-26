package hust.project.promotionservice.usecase;

import hust.project.promotionservice.constant.ApplyTo;
import hust.project.promotionservice.constant.DiscountType;
import hust.project.promotionservice.constant.ErrorCode;
import hust.project.promotionservice.constant.UsageType;
import hust.project.promotionservice.entity.PromotionApplyEntity;
import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.CreatePromotionRequest;
import hust.project.promotionservice.exception.AppException;
import hust.project.promotionservice.mapper.PromotionMapper;
import hust.project.promotionservice.port.IPromotionApplyPort;
import hust.project.promotionservice.port.IPromotionPort;
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
public class CreatePromotionUseCase {
    private final IPromotionPort promotionPort;
    private final IPromotionApplyPort promotionApplyPort;

    public PromotionEntity createPromotion(CreatePromotionRequest request) {
        if (request.getEndDate().toInstant().isBefore(request.getStartDate().toInstant())) {
            log.error("[CreatePromotionUseCase]: End date must be after start date");
            throw new AppException(ErrorCode.CREATE_PROMOTION_FAILED);
        }

        PromotionEntity promotion = PromotionMapper.INSTANCE.toEntityFromRequest(request);
        promotion.setApplyTo(ApplyTo.valueOf(request.getApplyTo()).name());
        promotion.setUsageType(UsageType.valueOf(request.getUsageType()).name());
        promotion.setDiscountType(DiscountType.valueOf(request.getDiscountType()).name());
        promotion.setStartDate(request.getStartDate().toInstant());
        promotion.setEndDate(request.getEndDate().toInstant());
        promotion.setUsageCount(0L);

        promotion = promotionPort.save(promotion);


        List<PromotionApplyEntity> promotionApplies = promotion.createPromotionApplies(request);
        if (!CollectionUtils.isEmpty(promotionApplies)) {
            promotion.setPromotionApplies(promotionApplyPort.saveAll(promotionApplies));
        }

        return promotion;
    }
}
