package hust.project.promotionservice.mapper;

import hust.project.promotionservice.entity.PromotionUsageEntity;
import hust.project.promotionservice.model.PromotionUsageModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class PromotionUsageMapper {
    public static final PromotionUsageMapper INSTANCE = Mappers.getMapper(PromotionUsageMapper.class);

    public abstract PromotionUsageEntity toEntityFromModel(PromotionUsageModel model);

    public abstract PromotionUsageModel toModelFromEntity(PromotionUsageEntity entity);
}
