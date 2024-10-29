package hust.project.productservice.mapper;

import hust.project.productservice.entity.ImageEntity;
import hust.project.productservice.model.ImageModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ImageMapper {
    public static final ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    public abstract ImageEntity toEntityFromModel(ImageModel imageModel);

    public abstract ImageModel toModelFromEntity(ImageEntity imageEntity);

    public abstract List<ImageEntity> toEntitiesFromModels(List<ImageModel> imageModels);

    public abstract List<ImageModel> toModelsFromEntities(List<ImageEntity> imageEntities);
}
