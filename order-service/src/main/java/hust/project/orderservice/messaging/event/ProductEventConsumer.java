package hust.project.orderservice.messaging.event;

import hust.project.common.event.ProductCreatedEvent;
import hust.project.common.event.ProductDeletedEvent;
import hust.project.common.event.ProductUpdatedEvent;
import hust.project.orderservice.entity.ProductEntity;
import hust.project.orderservice.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "${kafka.topic.product-event}")
@RequiredArgsConstructor
@Slf4j
public class ProductEventConsumer {
    private final IProductService productService;

    @KafkaHandler
    public void createProduct(ProductCreatedEvent event) {
        log.info("[ProductEventConsumer]: create product event: {}", event);
        productService.createProduct(ProductEntity.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .slug(event.getSlug())
                        .price(event.getPrice())
                        .isPublished(event.getIsPublished())
                .build());
    }

    @KafkaHandler
    public void updateProduct(ProductUpdatedEvent event) {
        log.info("[ProductEventConsumer]: update product event: {}", event);
        productService.updateProduct(ProductEntity.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .slug(event.getSlug())
                        .price(event.getPrice())
                        .isPublished(event.getIsPublished())
                .build());
    }

    @KafkaHandler
    public void deleteProduct(ProductDeletedEvent event) {
        log.info("[ProductEventConsumer]: delete product event: {}", event);
        productService.deleteProduct(event.getId());
    }
}
