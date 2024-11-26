package hust.project.promotionservice.repository.adapter;

import hust.project.promotionservice.constant.ErrorCode;
import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.GetPromotionRequest;
import hust.project.promotionservice.entity.dto.response.PageInfo;
import hust.project.promotionservice.exception.AppException;
import hust.project.promotionservice.mapper.PromotionMapper;
import hust.project.promotionservice.port.IPromotionPort;
import hust.project.promotionservice.repository.IPromotionRepository;
import hust.project.promotionservice.repository.specification.PromotionSpecification;
import hust.project.promotionservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionAdapter implements IPromotionPort {
    private final IPromotionRepository promotionRepository;

    @Override
    public PromotionEntity save(PromotionEntity promotionEntity) {
        try {
            return PromotionMapper.INSTANCE.toEntityFromModel(promotionRepository.save(
                    PromotionMapper.INSTANCE.toModelFromEntity(promotionEntity)
            ));
        } catch (Exception e) {
            log.error("[PromotionAdapter] save promotion failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PROMOTION_FAILED);
        }
    }

    @Override
    public PromotionEntity getPromotionById(Long id) {
        return PromotionMapper.INSTANCE.toEntityFromModel(promotionRepository.findById(id).orElse(null));
    }

    @Override
    public PromotionEntity getPromotionByCouponCode(String couponCode) {
        return PromotionMapper.INSTANCE.toEntityFromModel(
                promotionRepository.findByCouponCode(couponCode).orElse(null));
    }

    @Override
    public PromotionEntity getPromotionBySlug(String slug) {
        return PromotionMapper.INSTANCE.toEntityFromModel(
                promotionRepository.findBySlug(slug).orElse(null)
        );
    }

    @Override
    public Pair<PageInfo, List<PromotionEntity>> getAllPromotions(GetPromotionRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by("id").descending());

        var result = promotionRepository.findAll(PromotionSpecification.getAllPromotions(filter), pageable);
        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, PromotionMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public void deletePromotion(Long id) {
        try {
            promotionRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[PromotionAdapter] delete promotion failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_PROMOTION_FAILED);
        }
    }
}
