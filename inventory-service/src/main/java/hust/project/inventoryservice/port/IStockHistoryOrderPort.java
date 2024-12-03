package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.StockHistoryOrderEntity;

import java.util.List;

public interface IStockHistoryOrderPort {
    List<StockHistoryOrderEntity> saveAll(List<StockHistoryOrderEntity> stockHistoryOrderEntities);

    List<StockHistoryOrderEntity> getStockHistoryOrdersByOrderId(Long orderId);
}
