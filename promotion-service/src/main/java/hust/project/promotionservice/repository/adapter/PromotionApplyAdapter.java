package hust.project.promotionservice.repository.adapter;

import hust.project.promotionservice.constant.ErrorCode;
import hust.project.promotionservice.entity.PromotionApplyEntity;
import hust.project.promotionservice.exception.AppException;
import hust.project.promotionservice.mapper.PromotionApplyMapper;
import hust.project.promotionservice.model.PromotionApplyModel;
import hust.project.promotionservice.port.IPromotionApplyPort;
import hust.project.promotionservice.repository.IPromotionApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionApplyAdapter implements IPromotionApplyPort {
    private final IPromotionApplyRepository promotionApplyRepository;

    @Override
    public List<PromotionApplyEntity> saveAll(List<PromotionApplyEntity> promotionApplyEntities) {
        try {
            List<PromotionApplyModel> models = PromotionApplyMapper.INSTANCE.toModelsFromEntities(promotionApplyEntities);
            return PromotionApplyMapper.INSTANCE.toEntitiesFromModels(promotionApplyRepository.saveAll(models));
        } catch (Exception e) {
            log.error("[PromotionApplyAdapter] save promotion apply failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PROMOTION_APPLY_FAILED);
        }
    }

    @Override
    public List<PromotionApplyEntity> getPromotionAppliesByPromotionId(Long promotionId) {
        return PromotionApplyMapper.INSTANCE.toEntitiesFromModels(promotionApplyRepository.findByPromotionId(promotionId));
    }

    @Override
    public List<PromotionApplyEntity> getPromotionAppliesByPromotionIds(List<Long> promotionIds) {
        return PromotionApplyMapper.INSTANCE.toEntitiesFromModels(promotionApplyRepository.findByPromotionIdIn(promotionIds));
    }

    @Override
    public void deletePromotionAppliesByPromotionId(Long promotionId) {
        try {
            promotionApplyRepository.deleteByPromotionId(promotionId);
        } catch (Exception e) {
            log.error("[PromotionApplyAdapter] delete promotion apply failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_PROMOTION_APPLY_FAILED);
        }
    }
}
