package hust.project.inventoryservice.service;

import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.dto.request.CreateStockRequest;
import hust.project.inventoryservice.entity.dto.request.GetStockRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;

import java.util.List;

public interface IStockService {
    List<StockEntity> createStocks(List<CreateStockRequest> requests);

    List<StockEntity> updateProductQuantityInStocks(List<UpdateStockQuantityRequest> requests);

    List<StockEntity> getAllStocks(GetStockRequest request);
}
