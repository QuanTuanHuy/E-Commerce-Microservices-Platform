package hust.project.inventoryservice.service.impl;

import hust.project.inventoryservice.entity.StockHistoryOrderEntity;
import hust.project.inventoryservice.service.IStockHistoryOrderService;
import hust.project.inventoryservice.usecase.GetStockHistoryOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockHistoryOrderService implements IStockHistoryOrderService {
    private final GetStockHistoryOrderUseCase getStockHistoryOrderUseCase;

    @Override
    public List<StockHistoryOrderEntity> getStockHistoryOrdersByOrderId(Long orderId) {
        return getStockHistoryOrderUseCase.getStockHistoryOrdersByOrderId(orderId);
    }
}
