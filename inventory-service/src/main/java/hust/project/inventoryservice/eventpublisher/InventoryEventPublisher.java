package hust.project.inventoryservice.eventpublisher;

import hust.project.common.event.InventoryChangedEvent;
import hust.project.common.event.InventoryCheckedEvent;
import hust.project.common.event.InventoryFailedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryEventPublisher {
    public static final String INVENTORY_EVENT_TOPIC = "inventory-event";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishInventoryCheckedEvent(InventoryCheckedEvent inventoryCheckedEvent) {
        log.info("Publishing inventory checked event: {}", inventoryCheckedEvent);
        kafkaTemplate.send(INVENTORY_EVENT_TOPIC, inventoryCheckedEvent);
    }

    public void publishInventoryFailedEvent(InventoryFailedEvent inventoryFailedEvent) {
        log.info("Publishing inventory failed event: {}", inventoryFailedEvent);
        kafkaTemplate.send(INVENTORY_EVENT_TOPIC, inventoryFailedEvent);
    }

    public void publishInventoryChangedEvent(InventoryChangedEvent inventoryChangedEvent) {
        log.info("Publishing inventory changed event: {}", inventoryChangedEvent);
        kafkaTemplate.send(INVENTORY_EVENT_TOPIC, inventoryChangedEvent);
    }
}
