package hust.project.inventoryservice.repository;

import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;
import hust.project.inventoryservice.model.WarehouseModel;

import java.util.List;

public interface CustomWarehouseRepository {
    List<WarehouseModel> getAllWarehouses(GetWarehouseRequest filter);

}
