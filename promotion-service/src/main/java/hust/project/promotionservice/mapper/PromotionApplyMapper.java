package hust.project.promotionservice.mapper;

import hust.project.promotionservice.entity.PromotionApplyEntity;
import hust.project.promotionservice.model.PromotionApplyModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PromotionApplyMapper {
    public static final PromotionApplyMapper INSTANCE = Mappers.getMapper(PromotionApplyMapper.class);

    public abstract PromotionApplyEntity toEntityFromModel(PromotionApplyModel model);

    public abstract PromotionApplyModel toModelFromEntity(PromotionApplyEntity entity);

    public abstract List<PromotionApplyEntity> toEntitiesFromModels(List<PromotionApplyModel> models);

    public abstract List<PromotionApplyModel> toModelsFromEntities(List<PromotionApplyEntity> entities);
}
