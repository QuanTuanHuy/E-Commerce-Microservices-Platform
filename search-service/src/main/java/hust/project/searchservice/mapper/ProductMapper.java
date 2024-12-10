package hust.project.searchservice.mapper;

import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.CreateProductRequest;
import hust.project.searchservice.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract ProductModel toModelFromEntity(ProductEntity entity);

    public abstract ProductEntity toEntityFromModel(ProductModel model);

    public abstract ProductEntity toEntityFromRequest(CreateProductRequest request);
}
