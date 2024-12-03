package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.StockHistoryOrderEntity;
import hust.project.inventoryservice.entity.dto.request.ProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.port.IProductPort;
import hust.project.inventoryservice.port.IStockHistoryOrderPort;
import hust.project.inventoryservice.port.IStockPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    private final CreateStockHistoryUseCase createStockHistoryUseCase;
    private final IStockHistoryOrderPort stockHistoryOrderPort;

    public void decreaseProductQuantityInStockForCreateOrder(Long orderId, List<ProductQuantityRequest> requests) {
        // need to lock stock table
        List<Long> productIds = requests.stream()
                .map(ProductQuantityRequest::getProductId).toList();

        List<StockEntity> stocks = stockPort.getStocksByProductIds(productIds);

        List<StockHistoryOrderEntity> stockHistoryOrders = new ArrayList<>();

        requests.forEach(request -> {
            Integer requiredQuantity = request.getQuantity();

            List<StockEntity> currentStocks = stocks.stream()
                    .filter(stock -> stock.getProductId().equals(request.getProductId()))
                    .toList();

            if (CollectionUtils.isEmpty(currentStocks)) {
                throw new AppException(ErrorCode.STOCK_NOT_ENOUGH);
            }

            Long availableQuantity = currentStocks.stream().map(StockEntity::getAvailableQuantity)
                    .reduce(0L, Long::sum);

            if (availableQuantity < requiredQuantity) {
                throw new AppException(ErrorCode.STOCK_NOT_ENOUGH);
            }

            for (StockEntity st : currentStocks) {
                Integer subtractQuantity = Math.toIntExact(Math.min(st.getAvailableQuantity(), requiredQuantity));
                if (subtractQuantity > 0) {
                    st.setAvailableQuantity(st.getAvailableQuantity() - subtractQuantity);
                    st.setSoldQuantity(st.getSoldQuantity() + subtractQuantity);
                    requiredQuantity -= subtractQuantity;

                    stockHistoryOrders.add(StockHistoryOrderEntity.builder()
                            .stockId(st.getId())
                            .soldQuantity(subtractQuantity)
                            .orderId(orderId)
                            .build());
                }

                if (requiredQuantity == 0) {
                    break;
                }
            }
        });

        // fix it: now update stock is multiple times
        stockPort.saveAll(stocks);
        stockHistoryOrderPort.saveAll(stockHistoryOrders);
    }

    public void increaseProductQuantityInStockForCancelOrder(Long orderId) {
        List<StockHistoryOrderEntity> stockHistoryOrders = stockHistoryOrderPort.getStockHistoryOrdersByOrderId(orderId);

        List<StockEntity> stocks = stockPort.getStocksByIds(stockHistoryOrders.stream()
                .map(StockHistoryOrderEntity::getStockId).toList());


        var mapIdToStock = stocks.stream()
                .collect(Collectors.toMap(StockEntity::getId, Function.identity()));

        stockHistoryOrders.forEach(stockHistoryOrder -> {
            StockEntity stock = mapIdToStock.get(stockHistoryOrder.getStockId());
            stock.setAvailableQuantity(stock.getAvailableQuantity() + stockHistoryOrder.getSoldQuantity());
            stock.setSoldQuantity(stock.getSoldQuantity() - stockHistoryOrder.getSoldQuantity());
        });

        stockPort.saveAll(stocks);
    }


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


        // create stock history
        createStockHistoryUseCase.createStockHistories(requests, mapIdToStock);

        return savedStocks;
    }
}
