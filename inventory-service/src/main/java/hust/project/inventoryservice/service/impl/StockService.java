package hust.project.inventoryservice.service.impl;

import hust.project.common.event.InventoryCheckedEvent;
import hust.project.common.event.InventoryFailedEvent;
import hust.project.common.event.ProductQuantity;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.dto.request.CreateStockRequest;
import hust.project.inventoryservice.entity.dto.request.GetStockRequest;
import hust.project.inventoryservice.entity.dto.request.ProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.request.UpdateStockQuantityRequest;
import hust.project.inventoryservice.eventpublisher.InventoryEventPublisher;
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
    private final InventoryEventPublisher inventoryEventPublisher;

    @Override
    public List<StockEntity> createStocks(List<CreateStockRequest> requests) {
        return createStockUseCase.createStocks(requests);
    }

    @Override
    public List<StockEntity> updateProductQuantityInStocks(List<UpdateStockQuantityRequest> requests) {
        return updateStockUseCase.updateProductQuantityInStocks(requests);
    }

    @Override
    public void decreaseProductQuantityInStockForCreateOrder(Long orderId, List<ProductQuantityRequest> requests) {
        try {
            updateStockUseCase.decreaseProductQuantityInStockForCreateOrder(orderId, requests);
            inventoryEventPublisher.publishInventoryCheckedEvent(InventoryCheckedEvent.builder()
                            .orderId(orderId)
                    .build());
        } catch (Exception e) {
            inventoryEventPublisher.publishInventoryFailedEvent(InventoryFailedEvent.builder()
                            .orderId(orderId)
                    .build());
        }
    }

    @Override
    public void increaseProductQuantityInStockForCancelOrder(Long orderId) {
        updateStockUseCase.increaseProductQuantityInStockForCancelOrder(orderId);
    }

    @Override
    public List<ProductQuantity> getProductQuantitiesByProductIds(List<Long> productIds) {
        return getStockUseCase.getProductQuantitiesByProductIds(productIds);
    }

    @Override
    public List<StockEntity> getStocksByIds(List<Long> stockIds) {
        return List.of();
    }


    @Override
    public List<StockEntity> getAllStocks(GetStockRequest request) {
        return getStockUseCase.getAllStocks(request);
    }
}
