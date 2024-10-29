package hust.project.productservice.mapper;

import hust.project.productservice.entity.ProductCategoryEntity;
import hust.project.productservice.model.ProductCategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ProductCategoryMapper {
    public static final ProductCategoryMapper INSTANCE = Mappers.getMapper(ProductCategoryMapper.class);

    public abstract ProductCategoryEntity toEntityFromModel(ProductCategoryModel model);

    public abstract ProductCategoryModel toModelFromEntity(ProductCategoryEntity entity);

    public abstract List<ProductCategoryModel> toModelsFromEntities(List<ProductCategoryEntity> entities);

    public abstract List<ProductCategoryEntity> toEntitiesFromModels(List<ProductCategoryModel> models);
}
