package hust.project.orderservice.messaging.event;

import hust.project.common.event.ProductCreatedEvent;
import hust.project.common.event.ProductDeletedEvent;
import hust.project.common.event.ProductDomainEvent;
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
public class ProductEventConsumer implements IProductEventConsumer{
    private final IProductService productService;

    @Override
    @KafkaHandler
    public void handleProductDomainEvent(ProductDomainEvent event) {
        log.info("[ProductEventConsumer]: handle product event: {}", event);
        if (event instanceof ProductCreatedEvent) {
            createProduct((ProductCreatedEvent) event);
        } else if (event instanceof ProductUpdatedEvent) {
            updateProduct((ProductUpdatedEvent) event);
        } else if (event instanceof ProductDeletedEvent) {
            deleteProduct((ProductDeletedEvent) event);
        }
    }


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


    public void deleteProduct(ProductDeletedEvent event) {
        log.info("[ProductEventConsumer]: delete product event: {}", event);
        productService.deleteProduct(event.getId());
    }
}
