package hust.project.addressservice.mapper;

import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.CreateWardRequest;
import hust.project.addressservice.model.WardModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class WardMapper {
    public static final WardMapper INSTANCE = Mappers.getMapper(WardMapper.class);

    public abstract WardEntity toEntityFromModel(WardModel model);

    public abstract WardModel toModelFromEntity(WardEntity entity);

    public abstract WardEntity toEntityFromRequest(CreateWardRequest request);

    public abstract List<WardEntity> toEntitiesFromModels(List<WardModel> models);
}
