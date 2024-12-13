package hust.project.productservice.repository.adapter;

import hust.project.common.event.ProductCreatedEvent;
import hust.project.common.event.ProductDeletedEvent;
import hust.project.common.event.ProductUpdatedEvent;
import hust.project.productservice.port.IProductEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductEventAdapter implements IProductEventPort {
    @Value("${kafka.topic.product-event}")
    private String PRODUCT_EVENT_TOPIC;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        log.info("[ProductEventAdapter] send product created event, productCreatedEvent: {}", productCreatedEvent);
        kafkaTemplate.send(PRODUCT_EVENT_TOPIC, productCreatedEvent);
    }

    @Override
    public void sendProductDeletedEvent(ProductDeletedEvent productDeletedEvent) {
        log.info("[ProductEventAdapter] send product deleted event, productDeletedEvent: {}", productDeletedEvent);
        kafkaTemplate.send(PRODUCT_EVENT_TOPIC, productDeletedEvent);
    }

    @Override
    public void sendProductUpdatedEvent(ProductUpdatedEvent productUpdatedEvent) {
        log.info("[ProductEventAdapter] send product updated event, productUpdatedEvent: {}", productUpdatedEvent);
        kafkaTemplate.send(PRODUCT_EVENT_TOPIC, productUpdatedEvent);
    }
}
