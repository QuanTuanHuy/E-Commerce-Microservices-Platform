package hust.project.promotionservice.usecase;

import com.nimbusds.jose.util.Pair;
import hust.project.promotionservice.constant.ApplyTo;
import hust.project.promotionservice.constant.ErrorCode;
import hust.project.promotionservice.entity.PromotionApplyEntity;
import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.GetPromotionRequest;
import hust.project.promotionservice.entity.dto.response.*;
import hust.project.promotionservice.exception.AppException;
import hust.project.promotionservice.mapper.PromotionMapper;
import hust.project.promotionservice.port.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPromotionUseCase {
    private final IPromotionPort promotionPort;
    private final IPromotionApplyPort promotionApplyPort;
    private final IProductPort productPort;
    private final ICategoryPort categoryPort;
    private final IBrandPort brandPort;

    public Pair<PageInfo, List<PromotionDetailModel>> getAllPromotions(GetPromotionRequest filter) {
        var result = promotionPort.getAllPromotions(filter);
        List<PromotionEntity> promotionEntities = result.getSecond();
        if (CollectionUtils.isEmpty(promotionEntities)) {
            return Pair.of(result.getFirst(), List.of());
        }

        List<PromotionDetailModel> promotionDetails = promotionEntities.stream()
                .map(PromotionMapper.INSTANCE::toDetailModelFromEntity).toList();

        List<Long> promotionIds = promotionDetails.stream()
                .map(PromotionDetailModel::getId).toList();


        List<PromotionApplyEntity> promotionApplies = promotionApplyPort.getPromotionAppliesByPromotionIds(promotionIds);

        List<Long> allProductIds = new ArrayList<>(),
                allCategoryIds = new ArrayList<>(),
                allBrandIds = new ArrayList<>();

        promotionApplies.forEach(promotionApply -> {
            if (promotionApply.getProductId() != null) {
                allProductIds.add(promotionApply.getProductId());
            }
            else if (promotionApply.getBrandId() != null) {
                allBrandIds.add(promotionApply.getBrandId());
            }
            else if (promotionApply.getCategoryId() != null) {
                allCategoryIds.add(promotionApply.getCategoryId());
            }
        });

        List<ProductGetModel> allProducts = new ArrayList<>();
        List<BrandGetModel> allBrands = new ArrayList<>();
        List<CategoryGetModel> allCategories = new ArrayList<>();

        if (!CollectionUtils.isEmpty(allProductIds)) {
            allProducts = productPort.getProductsByIds(allProductIds);
        }
        if (!CollectionUtils.isEmpty(allBrandIds)) {
            allBrands = brandPort.getBrandsByIds(allBrandIds);
        }
        if (!CollectionUtils.isEmpty(allCategoryIds)) {
            allCategories = categoryPort.getCategoryByIds(allCategoryIds);
        }


        List<ProductGetModel> finalAllProducts = allProducts;
        List<CategoryGetModel> finalAllCategories = allCategories;
        List<BrandGetModel> finalAllBrands = allBrands;

        promotionDetails.forEach(promotionDetail -> {
            List<PromotionApplyEntity> currentPromotionApplies = promotionApplies.stream()
                    .filter(pa -> pa.getPromotionId().equals(promotionDetail.getId()))
                    .toList();

            switch (ApplyTo.valueOf(promotionDetail.getApplyTo())) {
                case PRODUCT:
                    Set<Long> productIds = currentPromotionApplies.stream()
                            .map(PromotionApplyEntity::getProductId)
                            .collect(Collectors.toSet());
                    promotionDetail.setProducts(finalAllProducts.stream().filter(p -> productIds.contains(p.getId())).toList());
                    break;
                case CATEGORY:
                    Set<Long> categoryIds = currentPromotionApplies.stream()
                            .map(PromotionApplyEntity::getCategoryId)
                            .collect(Collectors.toSet());
                    promotionDetail.setCategories(finalAllCategories.stream().filter(c -> categoryIds.contains(c.getId())).toList());
                    break;
                case BRAND:
                    Set<Long> brandIds = currentPromotionApplies.stream()
                            .map(PromotionApplyEntity::getBrandId)
                            .collect(Collectors.toSet());
                    promotionDetail.setBrands(finalAllBrands.stream().filter(b -> brandIds.contains(b.getId())).toList());
                    break;
            }
        });

        return Pair.of(result.getFirst(), promotionDetails);
    }

    public PromotionDetailModel getDetailPromotion(Long id) {
        PromotionEntity promotion = promotionPort.getPromotionById(id);
        if (promotion == null) {
            log.error("[GetPromotionUseCase]: promotion not found, id: {}", id);
            throw new AppException(ErrorCode.GET_PROMOTION_FAILED);
        }

        List<PromotionApplyEntity> promotionApplies = promotionApplyPort.getPromotionAppliesByPromotionId(id);

        PromotionDetailModel promotionDetail = PromotionMapper.INSTANCE.toDetailModelFromEntity(promotion);

        switch (ApplyTo.valueOf(promotion.getApplyTo())) {
            case PRODUCT:
                promotionDetail.setProducts(productPort.getProductsByIds(promotionApplies.stream()
                        .map(PromotionApplyEntity::getProductId)
                                .toList()));
                break;
            case CATEGORY:
                promotionDetail.setCategories(categoryPort.getCategoryByIds(promotionApplies.stream()
                        .map(PromotionApplyEntity::getCategoryId)
                        .toList()));
                break;
            case BRAND:
                promotionDetail.setBrands(brandPort.getBrandsByIds(promotionApplies.stream()
                        .map(PromotionApplyEntity::getBrandId)
                        .toList()));
                break;
        }

        return promotionDetail;
    }

}
