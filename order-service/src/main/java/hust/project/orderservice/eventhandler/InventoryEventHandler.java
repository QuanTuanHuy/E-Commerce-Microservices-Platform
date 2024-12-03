package hust.project.orderservice.eventhandler;

import hust.project.common.event.InventoryCheckedEvent;
import hust.project.common.event.InventoryFailedEvent;
import hust.project.orderservice.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
@KafkaListener(topics = "inventory-event")
public class InventoryEventHandler {
    private final IOrderService orderService;

    @KafkaHandler
    public void handleInventoryCheckedEvent(InventoryCheckedEvent event) {
        log.info("Inventory checked event: {}", event);

        orderService.acceptOrder(event.getOrderId());
    }

    @KafkaHandler
    public void handleInventoryFailedEvent(InventoryFailedEvent event) {
        log.info("Inventory failed event: {}", event);
        orderService.rejectOrder(event.getOrderId());
    }
}
