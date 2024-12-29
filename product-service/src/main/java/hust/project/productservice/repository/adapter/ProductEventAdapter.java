package hust.project.productservice.repository.adapter;

import hust.project.common.event.ProductDomainEvent;
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
    public void publishProductDomainEvent(ProductDomainEvent event) {
        log.info("[ProductEventAdapter] publish product domain event, event: {}", event);
        kafkaTemplate.send(PRODUCT_EVENT_TOPIC, event);
    }
}
