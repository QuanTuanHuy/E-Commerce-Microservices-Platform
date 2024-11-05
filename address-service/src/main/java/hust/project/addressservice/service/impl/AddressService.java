package hust.project.addressservice.service.impl;

import hust.project.addressservice.entity.AddressEntity;
import hust.project.addressservice.entity.dto.request.CreateAddressRequest;
import hust.project.addressservice.entity.dto.request.UpdateAddressRequest;
import hust.project.addressservice.entity.dto.request.ValidateAddressRequest;
import hust.project.addressservice.entity.dto.response.ValidateAddressResponse;
import hust.project.addressservice.service.IAddressService;
import hust.project.addressservice.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService {
    private final CreateAddressUseCase createAddressUseCase;
    private final GetAddressUseCase getAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final DeleteAddressUseCase deleteAddressUseCase;
    private final ValidateAddressUseCase validateAddressUseCase;

    @Override
    public AddressEntity createAddress(CreateAddressRequest request) {
        return createAddressUseCase.createAddress(request);
    }

    @Override
    public AddressEntity getDetailAddress(Long id) {
        return getAddressUseCase.getDetailAddress(id);
    }

    @Override
    public List<AddressEntity> getAddressByIds(List<Long> ids) {
        return getAddressUseCase.getAddressByIds(ids);
    }

    @Override
    public AddressEntity updateAddress(Long id, UpdateAddressRequest request) {
        return updateAddressUseCase.updateAddress(id, request);
    }

    @Override
    public void deleteAddress(Long id) {
        deleteAddressUseCase.deleteAddress(id);
    }

    @Override
    public ValidateAddressResponse validateAddress(ValidateAddressRequest request) {
        return validateAddressUseCase.validateAddress(request);
    }
}
