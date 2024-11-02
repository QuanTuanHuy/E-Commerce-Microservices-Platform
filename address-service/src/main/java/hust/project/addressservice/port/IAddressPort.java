package hust.project.addressservice.port;

import hust.project.addressservice.entity.AddressEntity;

import java.util.List;

public interface IAddressPort {
    AddressEntity save(AddressEntity addressEntity);

    AddressEntity getAddressById(Long id);

    List<AddressEntity> getAddressByIds(List<Long> ids);

    void deleteAddress(Long id);
}
