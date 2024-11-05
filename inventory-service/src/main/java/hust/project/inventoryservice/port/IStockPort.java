package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.StockEntity;

import java.util.List;

public interface IStockPort {
    StockEntity save(StockEntity stockEntity);

    List<StockEntity> saveAll(List<StockEntity> stockEntities);

    StockEntity getStockById(Long id);

    List<StockEntity> getStocksByWarehouseId(Long warehouseId);

    List<StockEntity> getStocksByIds(List<Long> ids);

    List<StockEntity> getStocksByWarehouseIdAndProductIdIn(Long warehouseId, List<Long> productIds);

    void deleteStock(Long id);
}
