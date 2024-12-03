package hust.project.orderservice.eventpublisher;

import hust.project.common.event.OrderAcceptedEvent;
import hust.project.common.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String topic = "order-event";

    public void publishOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent) {
        log.info("Publishing order created event: {}", orderCreatedEvent);
        kafkaTemplate.send(topic, orderCreatedEvent);
    }

    public void publishOrderAcceptedEvent(OrderAcceptedEvent orderAcceptedEvent) {
        log.info("Publishing order accepted event: {}", orderAcceptedEvent);
        kafkaTemplate.send(topic, orderAcceptedEvent);
    }
}
