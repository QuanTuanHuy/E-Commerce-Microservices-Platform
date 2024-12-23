package hust.project.orderservice.mapper;

import hust.project.orderservice.entity.ProductEntity;
import hust.project.orderservice.model.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    public abstract ProductModel toModel(ProductEntity entity);

    public abstract ProductEntity toEntity(ProductModel model);
}
