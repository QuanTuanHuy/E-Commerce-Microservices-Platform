package hust.project.promotionservice.entity;

import hust.project.promotionservice.constant.ApplyTo;
import hust.project.promotionservice.constant.UsageType;
import hust.project.promotionservice.entity.dto.request.CreatePromotionRequest;
import hust.project.promotionservice.entity.dto.request.UpdatePromotionRequest;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionEntity {
    private Long id;

    private String name;

    private String slug;

    private String description;

    private String couponCode;

    private String discountType;

    private String usageType;

    private String applyTo;

    private Long usageLimit;

    private Long usageCount;

    private Long discountPercentage;

    private Long discountAmount;

    private Long minimumOrderAmount;

    private Boolean isActive;

    private Instant startDate;

    private Instant endDate;

    List<PromotionApplyEntity> promotionApplies;


    public List<PromotionApplyEntity> createPromotionApplies(CreatePromotionRequest request) {
        return switch (ApplyTo.valueOf(request.getApplyTo())) {
            case PRODUCT -> request.getProductIds().stream()
                    .map(productId -> PromotionApplyEntity.builder()
                            .promotionId(id)
                            .productId(productId)
                            .build())
                    .toList();
            case CATEGORY -> request.getCategoryIds().stream()
                    .map(categoryId -> PromotionApplyEntity.builder()
                            .promotionId(id)
                            .categoryId(categoryId)
                            .build())
                    .toList();
            case BRAND -> request.getBrandIds().stream()
                    .map(brandId -> PromotionApplyEntity.builder()
                            .promotionId(id)
                            .brandId(brandId)
                            .build())
                    .toList();
        };
    }

    public List<PromotionApplyEntity> createPromotionApplies(UpdatePromotionRequest request) {
        return switch (ApplyTo.valueOf(request.getApplyTo())) {
            case PRODUCT -> request.getProductIds().stream()
                    .map(productId -> PromotionApplyEntity.builder()
                            .promotionId(id)
                            .productId(productId)
                            .build())
                    .toList();
            case CATEGORY -> request.getCategoryIds().stream()
                    .map(categoryId -> PromotionApplyEntity.builder()
                            .promotionId(id)
                            .categoryId(categoryId)
                            .build())
                    .toList();
            case BRAND -> request.getBrandIds().stream()
                    .map(brandId -> PromotionApplyEntity.builder()
                            .promotionId(id)
                            .brandId(brandId)
                            .build())
                    .toList();
        };
    }

    public boolean isCanUsePromotion(Long orderAmount) {
        return isActive && startDate.isBefore(Instant.now()) && endDate.isAfter(Instant.now()) &&
                (usageType.equals(UsageType.UNLIMITED.name()) || usageCount < usageLimit) &&
                (minimumOrderAmount == null || orderAmount >= minimumOrderAmount);
    }
}
