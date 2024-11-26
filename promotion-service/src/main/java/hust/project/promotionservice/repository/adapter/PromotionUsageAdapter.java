package hust.project.promotionservice.repository.adapter;

import hust.project.promotionservice.constant.ErrorCode;
import hust.project.promotionservice.entity.PromotionUsageEntity;
import hust.project.promotionservice.exception.AppException;
import hust.project.promotionservice.mapper.PromotionUsageMapper;
import hust.project.promotionservice.port.IPromotionUsagePort;
import hust.project.promotionservice.repository.IPromotionUsageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionUsageAdapter implements IPromotionUsagePort {
    private final IPromotionUsageRepository promotionUsageRepository;

    @Override
    public PromotionUsageEntity save(PromotionUsageEntity promotionUsageEntity) {
        try {
            return PromotionUsageMapper.INSTANCE.toEntityFromModel(
                    promotionUsageRepository.save(PromotionUsageMapper.INSTANCE.toModelFromEntity(promotionUsageEntity))
            );
        } catch (Exception e) {
            log.error("[PromotionUsageAdapter] save promotion usage failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PROMOTION_USAGE_FAILED);
        }
    }

    @Override
    public List<PromotionUsageEntity> getPromotionUsagesByPromotionId(Long promotionId) {
        return promotionUsageRepository.findByPromotionId(promotionId)
                .stream().map(PromotionUsageMapper.INSTANCE::toEntityFromModel)
                .toList();
    }
}
