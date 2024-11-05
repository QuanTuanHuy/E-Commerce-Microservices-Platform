package hust.project.inventoryservice.repository.adapter;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.WarehouseMapper;
import hust.project.inventoryservice.model.WarehouseModel;
import hust.project.inventoryservice.port.IWarehousePort;
import hust.project.inventoryservice.repository.IWarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseAdapter implements IWarehousePort {
    private final IWarehouseRepository warehouseRepository;

    @Override
    public WarehouseEntity save(WarehouseEntity warehouseEntity) {
        try {
            WarehouseModel warehouseModel = WarehouseMapper.INSTANCE.toModelFromEntity(warehouseEntity);
            return WarehouseMapper.INSTANCE.toEntityFromModel(warehouseRepository.save(warehouseModel));
        } catch (Exception e) {
            log.error("[WarehouseAdapter] save warehouse failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_WAREHOUSE_FAILED);
        }
    }

    @Override
    public WarehouseEntity getWarehouseById(Long id) {
        return WarehouseMapper.INSTANCE.toEntityFromModel(warehouseRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[WarehouseAdapter] get warehouse failed, id: {}", id);
                    return new AppException(ErrorCode.GET_WAREHOUSE_FAILED);
                }
        ));
    }

    @Override
    public List<WarehouseEntity> getAllWarehouses(GetWarehouseRequest filter) {
        return WarehouseMapper.INSTANCE.toEntitiesFromModels(warehouseRepository.getAllWarehouses(filter));
    }

    @Override
    public List<WarehouseEntity> getWarehousesByIds(List<Long> ids) {
        return WarehouseMapper.INSTANCE.toEntitiesFromModels(warehouseRepository.findByIdIn(ids));
    }

    @Override
    public void deleteWarehouse(Long id) {
        try {
            warehouseRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[WarehouseAdapter] delete warehouse failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_WAREHOUSE_FAILED);
        }
    }
}
