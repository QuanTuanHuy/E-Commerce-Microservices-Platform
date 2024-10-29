package hust.project.productservice.mapper;

import hust.project.productservice.entity.ProductRelatedEntity;
import hust.project.productservice.model.ProductRelatedModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ProductRelatedMapper {
    public static final ProductRelatedMapper INSTANCE = Mappers.getMapper(ProductRelatedMapper.class);

    public abstract ProductRelatedEntity toEntityFromModel(ProductRelatedModel model);

    public abstract ProductRelatedModel toModelFromEntity(ProductRelatedEntity entity);

    public abstract List<ProductRelatedEntity> toEntitiesFromModels(List<ProductRelatedModel> models);

    public abstract List<ProductRelatedModel> toModelsFromEntities(List<ProductRelatedEntity> entities);
}
