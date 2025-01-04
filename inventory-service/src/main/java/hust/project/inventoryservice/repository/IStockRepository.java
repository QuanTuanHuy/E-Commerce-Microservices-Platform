package hust.project.inventoryservice.repository;

import hust.project.inventoryservice.model.StockModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockRepository extends IBaseRepository<StockModel> {
    List<StockModel> findByWarehouseId(Long warehouseId);

    List<StockModel> findByWarehouseIdAndProductIdIn(Long warehouseId, List<Long> productIds);

    List<StockModel> findByProductIdIn(List<Long> productIds);

    List<StockModel> findByIdIn(List<Long> ids);
}
