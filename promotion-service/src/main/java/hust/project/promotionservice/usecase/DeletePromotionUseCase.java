package hust.project.promotionservice.usecase;

import hust.project.promotionservice.constant.ErrorCode;
import hust.project.promotionservice.exception.AppException;
import hust.project.promotionservice.port.IPromotionApplyPort;
import hust.project.promotionservice.port.IPromotionPort;
import hust.project.promotionservice.port.IPromotionUsagePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeletePromotionUseCase {
    private final IPromotionPort promotionPort;
    private final IPromotionApplyPort promotionApplyPort;
    private final IPromotionUsagePort promotionUsagePort;

    public void deletePromotion(Long id) {
        var promotionUsages = promotionUsagePort.getPromotionUsagesByPromotionId(id);
        if (!CollectionUtils.isEmpty(promotionUsages)) {
            log.error("[DeletePromotionUseCase]: promotion is used by some orders, id: {}", id);
            throw new AppException(ErrorCode.DELETE_PROMOTION_FAILED);
        }

        promotionPort.deletePromotion(id);
        promotionApplyPort.deletePromotionAppliesByPromotionId(id);
    }
}
