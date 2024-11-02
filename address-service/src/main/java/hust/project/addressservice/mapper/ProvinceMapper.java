package hust.project.addressservice.mapper;

import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.CreateProvinceRequest;
import hust.project.addressservice.model.ProvinceModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ProvinceMapper {
    public static final ProvinceMapper INSTANCE = Mappers.getMapper(ProvinceMapper.class);

    public abstract ProvinceModel toModelFromEntity(ProvinceEntity entity);

    public abstract ProvinceEntity toEntityFromModel(ProvinceModel model);

    public abstract ProvinceEntity toEntityFromRequest(CreateProvinceRequest request);

    public abstract List<ProvinceEntity> toEntitiesFromModels(List<ProvinceModel> models);
}
