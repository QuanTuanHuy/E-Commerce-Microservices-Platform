package hust.project.orderservice.mapper;

import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.CreateShippingAddressRequest;
import hust.project.orderservice.model.ShippingAddressModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class ShippingAddressMapper {
    public static final ShippingAddressMapper INSTANCE = Mappers.getMapper(ShippingAddressMapper.class);

    public abstract ShippingAddressEntity toEntityFromModel(ShippingAddressModel model);

    public abstract ShippingAddressModel toModelFromEntity(ShippingAddressEntity entity);

    public abstract ShippingAddressEntity toEntityFromRequest(CreateShippingAddressRequest request);

    public abstract List<ShippingAddressEntity> toEntitiesFromModels(List<ShippingAddressModel> models);
}
