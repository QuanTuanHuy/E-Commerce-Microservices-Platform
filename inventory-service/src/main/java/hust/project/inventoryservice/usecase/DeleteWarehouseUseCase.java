package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.port.IWarehouseAddressPort;
import hust.project.inventoryservice.port.IWarehousePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteWarehouseUseCase {
    private final IWarehousePort warehousePort;
    private final IWarehouseAddressPort warehouseAddressPort;

    public void deleteWarehouse(Long id) {
        WarehouseEntity warehouse = warehousePort.getWarehouseById(id);

        warehouseAddressPort.deleteWarehouseAddress(warehouse.getAddressId());
        warehousePort.deleteWarehouse(id);
    }
}
