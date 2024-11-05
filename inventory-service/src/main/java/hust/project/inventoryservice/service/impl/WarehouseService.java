package hust.project.inventoryservice.service.impl;

import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.CreateWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateWarehouseRequest;
import hust.project.inventoryservice.service.IWarehouseService;
import hust.project.inventoryservice.usecase.CreateWarehouseUseCase;
import hust.project.inventoryservice.usecase.DeleteWarehouseUseCase;
import hust.project.inventoryservice.usecase.GetWarehouseUseCase;
import hust.project.inventoryservice.usecase.UpdateWarehouseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseService implements IWarehouseService {
    private final CreateWarehouseUseCase createWarehouseUseCase;
    private final GetWarehouseUseCase getWarehouseUseCase;
    private final UpdateWarehouseUseCase updateWarehouseUseCase;
    private final DeleteWarehouseUseCase deleteWarehouseUseCase;

    @Override
    public WarehouseEntity createWarehouse(CreateWarehouseRequest request) {
        return createWarehouseUseCase.createWarehouse(request);
    }

    @Override
    public WarehouseEntity getDetailWarehouse(Long id) {
        return getWarehouseUseCase.getDetailWarehouse(id);
    }

    @Override
    public List<WarehouseEntity> getAllWarehouses(GetWarehouseRequest filter) {
        return getWarehouseUseCase.getAllWarehouses(filter);
    }

    @Override
    public WarehouseEntity updateWarehouse(Long id, UpdateWarehouseRequest request) {
        return updateWarehouseUseCase.updateWarehouse(id, request);
    }

    @Override
    public void deleteWarehouse(Long id) {
        deleteWarehouseUseCase.deleteWarehouse(id);
    }
}
