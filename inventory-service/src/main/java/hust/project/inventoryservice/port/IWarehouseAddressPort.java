package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.WarehouseAddressEntity;

import java.util.List;

public interface IWarehouseAddressPort {
    WarehouseAddressEntity save(WarehouseAddressEntity entity);

    WarehouseAddressEntity getWarehouseAddressById(Long id);

    List<WarehouseAddressEntity> getWarehouseAddressByIds(List<Long> ids);

    void deleteWarehouseAddress(Long id);
}
