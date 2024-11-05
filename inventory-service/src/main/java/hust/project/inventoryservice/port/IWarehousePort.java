package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;

import java.util.List;

public interface IWarehousePort {
    WarehouseEntity save(WarehouseEntity warehouseEntity);

    WarehouseEntity getWarehouseById(Long id);

    List<WarehouseEntity> getAllWarehouses(GetWarehouseRequest filter);

    List<WarehouseEntity> getWarehousesByIds(List<Long> ids);

    void deleteWarehouse(Long id);
}
