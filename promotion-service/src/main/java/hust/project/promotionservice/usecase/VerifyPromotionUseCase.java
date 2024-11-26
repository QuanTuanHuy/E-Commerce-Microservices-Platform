package hust.project.promotionservice.usecase;

import hust.project.promotionservice.constant.ApplyTo;
import hust.project.promotionservice.constant.DiscountType;
import hust.project.promotionservice.entity.PromotionApplyEntity;
import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.VerifyPromotionRequest;
import hust.project.promotionservice.entity.dto.response.ProductGetModel;
import hust.project.promotionservice.entity.dto.response.VerifyPromotionResult;
import hust.project.promotionservice.port.IProductPort;
import hust.project.promotionservice.port.IPromotionApplyPort;
import hust.project.promotionservice.port.IPromotionPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class VerifyPromotionUseCase {
    private final IPromotionPort promotionPort;
    private final IPromotionApplyPort promotionApplyPort;
    private final IProductPort productPort;


    public VerifyPromotionResult verifyPromotion(VerifyPromotionRequest request) {
        VerifyPromotionResult result = new VerifyPromotionResult();
        PromotionEntity promotion = promotionPort.getPromotionByCouponCode(request.getCouponCode());

        if (promotion == null) {
            log.error("[VerifyPromotionUseCase]: promotion not found, coupon code: {}", request.getCouponCode());
            result.setIsValid(false);
            return result;
        }

        if (!promotion.isCanUsePromotion(request.getOrderPrice())) {
            log.error("[VerifyPromotionUseCase]: promotion cannot use");
            result.setIsValid(false);
            return result;
        }


        List<PromotionApplyEntity> promotionApplies = promotionApplyPort.getPromotionAppliesByPromotionId(promotion.getId());
        List<Long> productIdsInPromotion = List.of();
        Set<Long> productIdsInOrder = request.getProductIds();

        switch (ApplyTo.valueOf(promotion.getApplyTo())) {
            case PRODUCT:
                productIdsInPromotion = promotionApplies.stream()
                        .map(PromotionApplyEntity::getProductId)
                        .toList();
                break;
            case CATEGORY:
                List<Long> categoryIds = promotionApplies.stream()
                        .map(PromotionApplyEntity::getCategoryId)
                        .toList();
                if (!CollectionUtils.isEmpty(categoryIds)) {
                    productIdsInPromotion = productPort.getProductsByCategoryIds(categoryIds)
                            .stream().map(ProductGetModel::getId)
                            .toList();
                }
                break;
            case BRAND:
                List<Long> brandIds = promotionApplies.stream()
                        .map(PromotionApplyEntity::getBrandId)
                        .toList();
                if (!CollectionUtils.isEmpty(brandIds)) {
                    productIdsInPromotion = productPort.getProductsByBrandIds(brandIds)
                            .stream().map(ProductGetModel::getId)
                            .toList();
                }
                break;
        }

        List<Long> commonProductIds = productIdsInPromotion.stream()
                .filter(productIdsInOrder::contains)
                .toList();


        if (CollectionUtils.isEmpty(commonProductIds)) {
            log.error("[VerifyPromotionUseCase]: not exist product in order can use promotion");
            result.setIsValid(false);
            return result;
        }


        result.setCouponCode(promotion.getCouponCode());
        result.setDiscountType(promotion.getDiscountType());
        result.setIsValid(true);
        if (promotion.getDiscountType().equals(DiscountType.FIXED.name())) {
            result.setDiscountValue(promotion.getDiscountAmount());
            result.setProductId(commonProductIds.get(0));
        } else {
            List<ProductGetModel> products = productPort.getProductsByIds(commonProductIds);
            products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));

            result.setProductId(products.get(0).getId());
            result.setDiscountValue((long) (products.get(0).getPrice() * promotion.getDiscountPercentage() / 100));
        }


        return result;
    }

}
