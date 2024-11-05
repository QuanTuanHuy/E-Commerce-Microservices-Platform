package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.StockHistoryEntity;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;
import hust.project.inventoryservice.port.IStockHistoryPort;
import hust.project.inventoryservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateStockHistoryUseCase {
    private final IStockHistoryPort stockHistoryPort;

    public void createStockHistories(List<UpdateStockQuantityRequest> requests, Map<Long, StockEntity> mapIdToStock) {
        final Long createdById = AuthenticationUtils.getCurrentUserId();

         var stockHistories = requests.stream().map(request -> {
            var stock = mapIdToStock.get(request.getStockId());

             return StockHistoryEntity.builder()
                    .productId(stock.getProductId())
                    .quantity(request.getQuantity())
                    .note(request.getNote())
                    .warehouseId(stock.getWarehouseId())
                    .createdById(createdById)
                    .build();
        }).toList();

        stockHistoryPort.saveAll(stockHistories);
    }
}
