package hust.project.productservice.mapper;

import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.dto.request.CreateBrandRequest;
import hust.project.productservice.entity.dto.response.BrandGetModel;
import hust.project.productservice.model.BrandModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class BrandMapper {
    public static final BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    public abstract BrandEntity toEntityFromModel(BrandModel model);

    @Mapping(target = "image", ignore = true)
    public abstract BrandEntity toEntityFromRequest(CreateBrandRequest request);

    public abstract BrandModel toModelFromEntity(BrandEntity entity);

    public abstract List<BrandEntity> toEntitiesFromModels(List<BrandModel> models);

    public abstract BrandGetModel toGetModelFromEntity(BrandEntity entity);
}
