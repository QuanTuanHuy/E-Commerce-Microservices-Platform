package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.CreateWarehouseRequest;
import hust.project.inventoryservice.model.WarehouseModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class WarehouseMapper {
    public static final WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    public abstract WarehouseEntity toEntityFromModel(WarehouseModel model);

    public abstract WarehouseModel toModelFromEntity(WarehouseEntity entity);

    public abstract WarehouseEntity toEntityFromRequest(CreateWarehouseRequest request);

    public abstract List<WarehouseEntity> toEntitiesFromModels(List<WarehouseModel> models);
}
