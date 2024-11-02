package hust.project.addressservice.repository.adapter;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.entity.AddressEntity;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.mapper.AddressMapper;
import hust.project.addressservice.model.AddressModel;
import hust.project.addressservice.port.IAddressPort;
import hust.project.addressservice.repository.IAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressAdapter implements IAddressPort {
    private final IAddressRepository addressRepository;

    @Override
    public AddressEntity save(AddressEntity addressEntity) {
        try {
            AddressModel addressModel = AddressMapper.INSTANCE.toModelFromEntity(addressEntity);
            return AddressMapper.INSTANCE.toEntityFromModel(addressRepository.save(addressModel));
        } catch (Exception e) {
            log.error("[AddressAdapter] save address failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ADDRESS_FAILED);
        }
    }

    @Override
    public AddressEntity getAddressById(Long id) {
        return AddressMapper.INSTANCE.toEntityFromModel(addressRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[AddressAdapter] get address by id failed, id: {}", id);
                    return new AppException(ErrorCode.GET_ADDRESS_FAILED);
                }
        ));
    }

    @Override
    public List<AddressEntity> getAddressByIds(List<Long> ids) {
        return AddressMapper.INSTANCE.toEntitiesFromModels(addressRepository.findByIdIn(ids));
    }

    @Override
    public void deleteAddress(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[AddressAdapter] delete address failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_ADDRESS_FAILED);
        }
    }
}
