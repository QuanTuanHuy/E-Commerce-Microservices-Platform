package hust.project.inventoryservice.usecase;

import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.dto.request.GetProductListRequest;
import hust.project.inventoryservice.entity.dto.request.GetStockRequest;
import hust.project.inventoryservice.entity.dto.response.ProductThumbnailResponse;
import hust.project.inventoryservice.port.IProductPort;
import hust.project.inventoryservice.port.IStockPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetStockUseCase {
    private final IStockPort stockPort;
    private final IProductPort productPort;

    public List<StockEntity> getAllStocks(GetStockRequest request) {
        if (!StringUtils.hasText(request.getProductSlug()) && !StringUtils.hasText(request.getProductName())) {
            return stockPort.getStocksByWarehouseId(request.getWarehouseId());
        }

        var allProductIdInWarehouse = stockPort.getStocksByWarehouseId(request.getWarehouseId())
                .stream()
                .map(StockEntity::getProductId)
                .toList();

        List<Long> productIds = productPort.getProductList(
                GetProductListRequest.builder()
                        .productIds(allProductIdInWarehouse)
                        .name(request.getProductName())
                        .slug(request.getProductSlug())
                        .build())
                .stream().map(ProductThumbnailResponse::getId)
                .toList();

        return stockPort.getStocksByWarehouseIdAndProductIdIn(request.getWarehouseId(), productIds);
    }
}
