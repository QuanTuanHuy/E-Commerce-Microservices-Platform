package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.entity.WarehouseAddressEntity;
import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;
import hust.project.inventoryservice.port.IWarehouseAddressPort;
import hust.project.inventoryservice.port.IWarehousePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetWarehouseUseCase {
    private final IWarehousePort warehousePort;
    private final IWarehouseAddressPort warehouseAddressPort;

    public WarehouseEntity getDetailWarehouse(Long id) {
        WarehouseEntity warehouse = warehousePort.getWarehouseById(id);
        warehouse.setAddress(warehouseAddressPort.getWarehouseAddressById(warehouse.getAddressId()));

        return warehouse;
    }

    public List<WarehouseEntity> getAllWarehouses(GetWarehouseRequest filter) {
        List<WarehouseEntity> warehouses = warehousePort.getAllWarehouses(filter);

        List<Long> addressIds = warehouses.stream().map(WarehouseEntity::getAddressId).toList();
        List<WarehouseAddressEntity> addressList = warehouseAddressPort.getWarehouseAddressByIds(addressIds);
        var mapIdToAddress = addressList.stream()
                .collect(Collectors.toMap(WarehouseAddressEntity::getId, Function.identity()));

        warehouses.forEach(warehouse -> warehouse.setAddress(mapIdToAddress.getOrDefault(warehouse.getAddressId(), null)));

        return warehouses;
    }

}
