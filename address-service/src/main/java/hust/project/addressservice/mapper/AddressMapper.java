package hust.project.addressservice.mapper;

import hust.project.addressservice.entity.AddressEntity;
import hust.project.addressservice.entity.dto.request.CreateAddressRequest;
import hust.project.addressservice.model.AddressModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class AddressMapper {
    public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    public abstract AddressEntity toEntityFromModel(AddressModel model);

    public abstract AddressModel toModelFromEntity(AddressEntity entity);

    public abstract AddressEntity toEntityFromRequest(CreateAddressRequest request);

    public abstract List<AddressEntity> toEntitiesFromModels(List<AddressModel> models);
}
