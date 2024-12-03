package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.entity.StockHistoryOrderEntity;
import hust.project.inventoryservice.port.IStockHistoryOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetStockHistoryOrderUseCase {
    private final IStockHistoryOrderPort stockHistoryOrderPort;

    public List<StockHistoryOrderEntity> getStockHistoryOrdersByOrderId(Long orderId) {
        return stockHistoryOrderPort.getStockHistoryOrdersByOrderId(orderId);
    }
}
