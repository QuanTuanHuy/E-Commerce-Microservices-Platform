package hust.project.promotionservice.service.impl;

import com.nimbusds.jose.util.Pair;
import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.*;
import hust.project.promotionservice.entity.dto.response.PageInfo;
import hust.project.promotionservice.entity.dto.response.PromotionDetailModel;
import hust.project.promotionservice.entity.dto.response.VerifyPromotionResult;
import hust.project.promotionservice.service.IPromotionService;
import hust.project.promotionservice.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {
    private final CreatePromotionUseCase createPromotionUseCase;
    private final GetPromotionUseCase getPromotionUseCase;
    private final UpdatePromotionUseCase updatePromotionUseCase;
    private final DeletePromotionUseCase deletePromotionUseCase;
    private final VerifyPromotionUseCase verifyPromotionUseCase;

    @Override
    public PromotionEntity createPromotion(CreatePromotionRequest request) {
        return createPromotionUseCase.createPromotion(request);
    }

    @Override
    public Pair<PageInfo, List<PromotionDetailModel>> getAllPromotions(GetPromotionRequest filter) {
        return getPromotionUseCase.getAllPromotions(filter);
    }

    @Override
    public PromotionDetailModel getDetailPromotion(Long id) {
        return getPromotionUseCase.getDetailPromotion(id);
    }

    @Override
    public VerifyPromotionResult verifyPromotion(VerifyPromotionRequest request) {
        return verifyPromotionUseCase.verifyPromotion(request);
    }

    @Override
    public PromotionEntity updatePromotion(Long id, UpdatePromotionRequest request) {
        return updatePromotionUseCase.updatePromotion(id, request);
    }

    @Override
    public void updatePromotionUsage(Long id, UpdatePromotionUsageRequest request) {
        updatePromotionUseCase.updatePromotionUsage(id, request);
    }

    @Override
    public void deletePromotion(Long id) {
        deletePromotionUseCase.deletePromotion(id);
    }
}
