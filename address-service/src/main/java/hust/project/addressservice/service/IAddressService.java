package hust.project.addressservice.service;

import hust.project.addressservice.entity.AddressEntity;
import hust.project.addressservice.entity.dto.request.CreateAddressRequest;
import hust.project.addressservice.entity.dto.request.UpdateAddressRequest;

import java.util.List;

public interface IAddressService {
    AddressEntity createAddress(CreateAddressRequest request);

    AddressEntity getDetailAddress(Long id);

    List<AddressEntity> getAddressByIds(List<Long> ids);

    AddressEntity updateAddress(Long id, UpdateAddressRequest request);

    void deleteAddress(Long id);
}