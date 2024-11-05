package hust.project.inventoryservice.service;

import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.CreateWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateWarehouseRequest;

import java.util.List;

public interface IWarehouseService {
    WarehouseEntity createWarehouse(CreateWarehouseRequest request);

    WarehouseEntity getDetailWarehouse(Long id);

    List<WarehouseEntity> getAllWarehouses(GetWarehouseRequest filter);

    WarehouseEntity updateWarehouse(Long id, UpdateWarehouseRequest request);

    void deleteWarehouse(Long id);
}
