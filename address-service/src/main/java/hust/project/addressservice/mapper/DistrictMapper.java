package hust.project.addressservice.mapper;

import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.CreateDistrictRequest;
import hust.project.addressservice.model.DistrictModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class DistrictMapper {
    public static final DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    public abstract DistrictEntity toEntityFromModel(DistrictModel model);

    public abstract DistrictModel toModelFromEntity(DistrictEntity entity);

    public abstract DistrictEntity toEntityFromRequest(CreateDistrictRequest request);

    public abstract List<DistrictEntity> toEntitiesFromModels(List<DistrictModel> models);
}
