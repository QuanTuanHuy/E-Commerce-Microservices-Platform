package hust.project.inventoryservice.repository;

import hust.project.inventoryservice.model.StockHistoryOrderModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStockHistoryOrderRepository extends IBaseRepository<StockHistoryOrderModel> {
    List<StockHistoryOrderModel> findByOrderId(Long orderId);
}
