package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.WarehouseAddressEntity;
import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.UpdateWarehouseAddressRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.ValidateAddressRequest;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.port.IAddressPort;
import hust.project.inventoryservice.port.IWarehouseAddressPort;
import hust.project.inventoryservice.port.IWarehousePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateWarehouseUseCase {
    private final IWarehousePort warehousePort;
    private final IWarehouseAddressPort warehouseAddressPort;
    private final IAddressPort addressPort;

    @Transactional
    public WarehouseEntity updateWarehouse(Long id, UpdateWarehouseRequest request) {
        validateAddress(request.getAddressRequest());

        WarehouseEntity warehouse = warehousePort.getWarehouseById(id);

        warehouse.setName(request.getName());
        warehouse.setCode(request.getCode());

        WarehouseAddressEntity address = warehouseAddressPort.getWarehouseAddressById(warehouse.getAddressId());
        var addressRequest = request.getAddressRequest();

        addressRequest.setContactName(addressRequest.getContactName());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setAddressDetail(addressRequest.getAddressDetail());
        address.setProvinceId(addressRequest.getProvinceId());
        address.setProvinceName(addressRequest.getProvinceName());
        address.setDistrictId(addressRequest.getDistrictId());
        address.setDistrictName(addressRequest.getDistrictName());
        address.setWardId(addressRequest.getWardId());
        address.setWardName(addressRequest.getWardName());

        warehousePort.save(warehouse);

        warehouse.setAddress(warehouseAddressPort.save(address));
        return warehouse;
    }

    public void validateAddress(UpdateWarehouseAddressRequest request) {
        ValidateAddressRequest validateAddressRequest = ValidateAddressRequest.builder()
                .provinceId(request.getProvinceId())
                .provinceName(request.getProvinceName())
                .districtId(request.getDistrictId())
                .districtName(request.getDistrictName())
                .wardId(request.getWardId())
                .wardName(request.getWardName())
                .build();

        var isValid = addressPort.validateAddress(validateAddressRequest).getIsValid();
        if (!isValid) {
            log.error("[CreateWarehouseUseCase] address is not valid");
            throw new AppException(ErrorCode.CREATE_WAREHOUSE_FAILED);
        }
    }
}
