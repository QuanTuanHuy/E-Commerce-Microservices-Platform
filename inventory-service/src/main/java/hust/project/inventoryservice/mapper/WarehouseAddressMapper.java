package hust.project.inventoryservice.mapper;

import hust.project.inventoryservice.entity.WarehouseAddressEntity;
import hust.project.inventoryservice.entity.dto.request.CreateWarehouseAddressRequest;
import hust.project.inventoryservice.model.WarehouseAddressModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class WarehouseAddressMapper {
    public static final WarehouseAddressMapper INSTANCE = Mappers.getMapper(WarehouseAddressMapper.class);

    public abstract WarehouseAddressEntity toEntityFromModel(WarehouseAddressModel model);

    public abstract WarehouseAddressModel toModelFromEntity(WarehouseAddressEntity entity);

    public abstract WarehouseAddressEntity toEntityFromRequest(CreateWarehouseAddressRequest request);

    public abstract List<WarehouseAddressEntity> toEntitiesFromModels(List<WarehouseAddressModel> models);
}
