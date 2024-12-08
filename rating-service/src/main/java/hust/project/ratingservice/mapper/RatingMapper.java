package hust.project.ratingservice.mapper;

import hust.project.ratingservice.entity.RatingEntity;
import hust.project.ratingservice.model.RatingModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class RatingMapper {
    public static final RatingMapper INSTANCE = Mappers.getMapper(RatingMapper.class);

    public abstract RatingModel toModelFromEntity(RatingEntity entity);

    public abstract RatingEntity toEntityFromModel(RatingModel model);
}
