package hust.project.inventoryservice.repository.adapter;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.WarehouseAddressEntity;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.WarehouseAddressMapper;
import hust.project.inventoryservice.model.WarehouseAddressModel;
import hust.project.inventoryservice.port.IWarehouseAddressPort;
import hust.project.inventoryservice.repository.IWarehouseAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WarehouseAddressAdapter implements IWarehouseAddressPort {
    private final IWarehouseAddressRepository warehouseAddressRepository;

    @Override
    public WarehouseAddressEntity save(WarehouseAddressEntity entity) {
        try {
            WarehouseAddressModel model = WarehouseAddressMapper.INSTANCE.toModelFromEntity(entity);
            return WarehouseAddressMapper.INSTANCE.toEntityFromModel(warehouseAddressRepository.save(model));
        } catch (Exception e) {
            log.error("[WarehouseAddressAdapter] save warehouse address failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_WAREHOUSE_ADDRESS_FAILED);
        }
    }

    @Override
    public WarehouseAddressEntity getWarehouseAddressById(Long id) {
        return WarehouseAddressMapper.INSTANCE.toEntityFromModel(warehouseAddressRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[WarehouseAddressAdapter] get warehouse address failed, id: {}", id);
                    return new AppException(ErrorCode.GET_WAREHOUSE_ADDRESS_FAILED);
                }
        ));
    }

    @Override
    public List<WarehouseAddressEntity> getWarehouseAddressByIds(List<Long> ids) {
        return WarehouseAddressMapper.INSTANCE.toEntitiesFromModels(warehouseAddressRepository.findByIdIn(ids));
    }

    @Override
    public void deleteWarehouseAddress(Long id) {
        try {
            warehouseAddressRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[WarehouseAddressAdapter] delete warehouse address failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_WAREHOUSE_ADDRESS_FAILED);
        }
    }
}
