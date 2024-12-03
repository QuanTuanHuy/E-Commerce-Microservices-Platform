package hust.project.inventoryservice.eventhandler;

import hust.project.common.event.InventoryChangedEvent;
import hust.project.common.event.OrderAcceptedEvent;
import hust.project.common.event.OrderCreatedEvent;
import hust.project.common.event.ProductQuantity;
import hust.project.inventoryservice.entity.StockEntity;
import hust.project.inventoryservice.entity.StockHistoryOrderEntity;
import hust.project.inventoryservice.entity.dto.request.ProductQuantityRequest;
import hust.project.inventoryservice.eventpublisher.InventoryEventPublisher;
import hust.project.inventoryservice.service.IStockHistoryOrderService;
import hust.project.inventoryservice.service.IStockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "order-event")
@Slf4j
public class OrderEventHandler {
    private final IStockService stockService;
    private final IStockHistoryOrderService stockHistoryOrderService;
    private final InventoryEventPublisher inventoryEventPublisher;

    @KafkaHandler
    public void handleOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        List<ProductQuantityRequest> requests = orderCreatedEvent.getOrderItems().stream()
                .map(oi -> ProductQuantityRequest.builder()
                        .productId(oi.getProductId())
                        .quantity(oi.getQuantity())
                        .build())
                .toList();

        stockService.decreaseProductQuantityInStockForCreateOrder(orderCreatedEvent.getOrderId(), requests);
    }

    @KafkaHandler
    public void handleOrderAcceptedEvent(OrderAcceptedEvent orderAcceptedEvent) {
        log.info("Order accepted event: {}", orderAcceptedEvent);

        List<StockHistoryOrderEntity> stockHistoryOrders = stockHistoryOrderService
                .getStockHistoryOrdersByOrderId(orderAcceptedEvent.getOrderId());

        List<Long> stockIds = stockHistoryOrders.stream()
                .map(StockHistoryOrderEntity::getStockId)
                .toList();

        List<Long> productIds = stockService.getStocksByIds(stockIds).stream()
                .map(StockEntity::getProductId)
                .toList();

        List<ProductQuantity> productQuantities = stockService.getProductQuantitiesByProductIds(productIds);

        inventoryEventPublisher.publishInventoryChangedEvent(InventoryChangedEvent.builder()
                        .productQuantities(productQuantities)
                .build());
    }
}
