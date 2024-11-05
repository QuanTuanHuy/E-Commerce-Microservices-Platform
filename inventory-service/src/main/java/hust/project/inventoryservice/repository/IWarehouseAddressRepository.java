package hust.project.inventoryservice.repository;

import hust.project.inventoryservice.model.WarehouseAddressModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWarehouseAddressRepository extends IBaseRepository<WarehouseAddressModel> {
    List<WarehouseAddressModel> findByIdIn(List<Long> ids);
}
