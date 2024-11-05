package hust.project.inventoryservice.service.impl;

import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.dto.request.CreateStockRequest;
import hust.project.inventoryservice.entity.dto.request.GetStockRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;
import hust.project.inventoryservice.service.IStockService;
import hust.project.inventoryservice.usecase.CreateStockUseCase;
import hust.project.inventoryservice.usecase.GetStockUseCase;
import hust.project.inventoryservice.usecase.UpdateStockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    private final CreateStockUseCase createStockUseCase;
    private final UpdateStockUseCase updateStockUseCase;
    private final GetStockUseCase getStockUseCase;

    @Override
    public List<StockEntity> createStocks(List<CreateStockRequest> requests) {
        return createStockUseCase.createStocks(requests);
    }

    @Override
    public List<StockEntity> updateProductQuantityInStocks(List<UpdateStockQuantityRequest> requests) {
        return updateStockUseCase.updateProductQuantityInStocks(requests);
    }

    @Override
    public List<StockEntity> getAllStocks(GetStockRequest request) {
        return getStockUseCase.getAllStocks(request);
    }
}
