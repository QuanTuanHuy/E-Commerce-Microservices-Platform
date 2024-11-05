package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.constants.ErrorCode;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.WarehouseEntity;
import hust.project.inventoryservice.entity.dto.request.CreateStockRequest;
import hust.project.inventoryservice.entity.dto.request.GetProductListRequest;
import hust.project.inventoryservice.entity.dto.response.ProductThumbnailResponse;
import hust.project.inventoryservice.exception.AppException;
import hust.project.inventoryservice.port.IProductPort;
import hust.project.inventoryservice.port.IStockPort;
import hust.project.inventoryservice.port.IWarehousePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateStockUseCase {
    private final IStockPort stockPort;
    private final IWarehousePort warehousePort;
    private final IProductPort productPort;

    public List<StockEntity> createStocks(List<CreateStockRequest> requests) {
        if (CollectionUtils.isEmpty(requests)) {
            return List.of();
        }

        List<Long> warehouseIds = requests.stream()
                .map(CreateStockRequest::getWarehouseId)
                .distinct().toList();
        List<WarehouseEntity> warehouses = warehousePort.getWarehousesByIds(warehouseIds);

        if (warehouses.size() != warehouseIds.size()) {
            log.error("[CreateStockUseCase] create stock failed: warehouse not found");
            throw new AppException(ErrorCode.CREATE_STOCK_FAILED);
        }


        List<Long> productIds = requests.stream()
                .map(CreateStockRequest::getProductId)
                .distinct().toList();
        List<ProductThumbnailResponse> products = productPort.getProductList(
                GetProductListRequest.builder().productIds(productIds).build());

        if (products.size() != productIds.size()) {
            log.error("[CreateStockUseCase] create stock failed: product not found");
            throw new AppException(ErrorCode.CREATE_STOCK_FAILED);
        }

        List<StockEntity> stocks = requests.stream()
                .map(request -> StockEntity.builder()
                        .productId(request.getProductId())
                        .warehouseId(request.getWarehouseId())
                        .availableQuantity(0L)
                        .soldQuantity(0L)
                        .build())
                .toList();

        return stockPort.saveAll(stocks);
    }
}
