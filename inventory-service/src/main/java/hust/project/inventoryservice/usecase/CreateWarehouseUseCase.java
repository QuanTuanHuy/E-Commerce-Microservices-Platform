package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.WarehouseAddressEntity;
import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.CreateWarehouseAddressRequest;
import hust.project.inventoryservice.entity.dto.request.CreateWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.ValidateAddressRequest;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.WarehouseAddressMapper;
import hust.project.inventoryservice.mapper.WarehouseMapper;
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
public class CreateWarehouseUseCase {
    private final IWarehousePort warehousePort;
    private final IWarehouseAddressPort warehouseAddressPort;
    private final IAddressPort addressPort;

    @Transactional
    public WarehouseEntity createWarehouse(CreateWarehouseRequest request) {
        validateAddress(request.getAddressRequest());
        
        WarehouseEntity warehouse = WarehouseMapper.INSTANCE.toEntityFromRequest(request);

        WarehouseAddressEntity address = WarehouseAddressMapper.INSTANCE.toEntityFromRequest(request.getAddressRequest());
        address = warehouseAddressPort.save(address);

        warehouse.setAddressId(address.getId());

        var savedWarehouse = warehousePort.save(warehouse);
        savedWarehouse.setAddress(address);

        return savedWarehouse;
    }

    public void validateAddress(CreateWarehouseAddressRequest request) {
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
