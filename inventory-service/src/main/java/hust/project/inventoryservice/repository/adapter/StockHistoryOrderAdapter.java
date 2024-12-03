package hust.project.inventoryservice.repository.adapter;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.StockHistoryOrderEntity;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.mapper.StockHistoryOrderMapper;
import hust.project.inventoryservice.model.StockHistoryOrderModel;
import hust.project.inventoryservice.port.IStockHistoryOrderPort;
import hust.project.inventoryservice.repository.IStockHistoryOrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockHistoryOrderAdapter implements IStockHistoryOrderPort {
    private final IStockHistoryOrderRepository stockHistoryOrderRepository;

    @Override
    public List<StockHistoryOrderEntity> saveAll(List<StockHistoryOrderEntity> entities) {
        try {
            List<StockHistoryOrderModel> models = entities.stream()
                    .map(StockHistoryOrderMapper.INSTANCE::toModelFromEntity)
                    .toList();

            return stockHistoryOrderRepository.saveAll(models).stream()
                    .map(StockHistoryOrderMapper.INSTANCE::toEntityFromModel)
                    .toList();
        } catch (Exception e) {
            log.error("[StockHistoryOrderAdapter] save stock history order failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_STOCK_HISTORY_ORDER_FAILED);
        }
    }

    @Override
    public List<StockHistoryOrderEntity> getStockHistoryOrdersByOrderId(Long orderId) {
        return stockHistoryOrderRepository.findByOrderId(orderId).stream()
                .map(StockHistoryOrderMapper.INSTANCE::toEntityFromModel)
                .toList();
    }
}
