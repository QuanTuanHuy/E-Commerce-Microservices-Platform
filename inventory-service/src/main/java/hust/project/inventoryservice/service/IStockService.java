package hust.project.inventoryservice.service;

import hust.project.common.event.ProductQuantity;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.dto.request.CreateStockRequest;
import hust.project.inventoryservice.entity.dto.request.GetStockRequest;
import hust.project.inventoryservice.entity.dto.request.ProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;

import java.util.List;

public interface IStockService {
    List<StockEntity> createStocks(List<CreateStockRequest> requests);

    List<StockEntity> updateProductQuantityInStocks(List<UpdateStockQuantityRequest> requests);

    void decreaseProductQuantityInStockForCreateOrder(Long orderId, List<ProductQuantityRequest> requests);

    void increaseProductQuantityInStockForCancelOrder(Long orderId);

    List<ProductQuantity> getProductQuantitiesByProductIds(List<Long> productIds);

    List<StockEntity> getStocksByIds(List<Long> stockIds);

    List<StockEntity> getAllStocks(GetStockRequest request);
}
