package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.port.IProductPort;
import hust.project.inventoryservice.port.IStockPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UpdateStockUseCase {
    private final IStockPort stockPort;
    private final IProductPort productPort;

    public List<StockEntity> updateProductQuantityInStocks(List<UpdateStockQuantityRequest> requests) {

        if (CollectionUtils.isEmpty(requests)) {
            return List.of();
        }

        List<Long> stockIds = requests.stream()
                .map(UpdateStockQuantityRequest::getStockId)
                .distinct().toList();

        List<StockEntity> stocks = stockPort.getStocksByIds(stockIds);

        if (stocks.size() != stockIds.size()) {
            log.error("[UpdateStockUseCase] update stock failed: stock not found");
            throw new AppException(ErrorCode.UPDATE_STOCK_FAILED);
        }

        var mapIdToStock = stocks.stream()
                .collect(Collectors.toMap(StockEntity::getId, Function.identity()));


        requests.forEach(request -> {
            StockEntity stock = mapIdToStock.get(request.getStockId());
            stock.setAvailableQuantity(stock.getAvailableQuantity() + request.getQuantity());
        });

        var savedStocks = stockPort.saveAll(stocks);


        // sync product quantity
        List<UpdateProductQuantityRequest> productQuantityRequests = requests.stream()
                .map(request -> UpdateProductQuantityRequest.builder()
                        .productId(mapIdToStock.get(request.getStockId()).getProductId())
                        .quantity(request.getQuantity())
                        .build())
                .toList();


        productPort.updateProductQuantity(productQuantityRequests);

        return savedStocks;
    }
}
