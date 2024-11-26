package hust.project.promotionservice.mapper;

import hust.project.promotionservice.entity.PromotionEntity;
import hust.project.promotionservice.entity.dto.request.CreatePromotionRequest;
import hust.project.promotionservice.entity.dto.response.PromotionDetailModel;
import hust.project.promotionservice.model.PromotionModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PromotionMapper {
    public static final PromotionMapper INSTANCE = Mappers.getMapper(PromotionMapper.class);

    public abstract PromotionModel toModelFromEntity(PromotionEntity entity);

    public abstract PromotionEntity toEntityFromModel(PromotionModel model);

    public abstract List<PromotionEntity> toEntitiesFromModels(List<PromotionModel> models);

    @Mapping(target = "startDate", ignore = true)
    @Mapping(target = "endDate", ignore = true)
    @Mapping(target = "usageType", ignore = true)
    @Mapping(target = "discountType", ignore = true)
    @Mapping(target = "applyTo", ignore = true)
    public abstract PromotionEntity toEntityFromRequest(CreatePromotionRequest request);

    public abstract PromotionDetailModel toDetailModelFromEntity(PromotionEntity entity);
}
