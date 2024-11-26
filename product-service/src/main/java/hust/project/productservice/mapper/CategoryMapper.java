package hust.project.productservice.mapper;

import hust.project.productservice.entity.CategoryEntity;
import hust.project.productservice.entity.dto.request.CreateCategoryRequest;
import hust.project.productservice.entity.dto.response.CategoryGetModel;
import hust.project.productservice.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class CategoryMapper {
    public static final CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    public abstract CategoryEntity toEntityFromModel(CategoryModel model);

    public abstract CategoryModel toModelFromEntity(CategoryEntity entity);

    public abstract List<CategoryEntity> toEntitiesFromModels(List<CategoryModel> models);

    @Mapping(target = "image", ignore = true)
    public abstract CategoryEntity toEntityFromRequest(CreateCategoryRequest request);

    public abstract CategoryGetModel toGetModelFromEntity(CategoryEntity entity);
}
