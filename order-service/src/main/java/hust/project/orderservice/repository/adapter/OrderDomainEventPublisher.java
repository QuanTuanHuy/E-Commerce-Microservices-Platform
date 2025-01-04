package hust.project.orderservice.repository.adapter;

import hust.project.common.event.OrderDomainEvent;
import hust.project.orderservice.port.IOrderDomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDomainEventPublisher implements IOrderDomainEventPublisher {
    @Value("${kafka.topic.order-event}")
    private String TOPIC;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishOrderDomainEvent(OrderDomainEvent event) {
        log.info("[OrderDomainEventPublisher] Publish order event: {}", event);
        kafkaTemplate.send(TOPIC, event);
    }
}
