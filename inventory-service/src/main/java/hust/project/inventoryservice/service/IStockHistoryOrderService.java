package hust.project.inventoryservice.service;

import hust.project.inventoryservice.entity.StockHistoryOrderEntity;

import java.util.List;

public interface IStockHistoryOrderService {
    List<StockHistoryOrderEntity> getStockHistoryOrdersByOrderId(Long orderId);
}
