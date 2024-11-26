package hust.project.productservice.mapper;

import hust.project.productservice.entity.ProductEntity;
import hust.project.productservice.entity.dto.request.CreateProductRequest;
import hust.project.productservice.entity.dto.response.ProductGetModel;
import hust.project.productservice.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract ProductEntity toEntityFromModel(ProductModel model);

    @Mapping(target = "images", ignore = true)
    @Mapping(target = "productVariants", ignore = true)
    public abstract ProductEntity toEntityFromRequest(CreateProductRequest request);

    public abstract ProductModel toModelFromEntity(ProductEntity entity);

    public abstract List<ProductEntity> toEntitiesFromModels(List<ProductModel> models);

    public abstract List<ProductModel> toModelsFromEntities(List<ProductEntity> entities);

    public abstract ProductGetModel toGetModelFromEntity(ProductEntity entity);
}
