package hust.project.inventoryservice.repository;

import hust.project.inventoryservice.model.WarehouseModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWarehouseRepository extends IBaseRepository<WarehouseModel>, CustomWarehouseRepository {
    List<WarehouseModel> findByIdIn(List<Long> ids);
}
