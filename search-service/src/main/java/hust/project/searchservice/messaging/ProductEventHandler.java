package hust.project.searchservice.messaging;

import hust.project.common.event.ProductCreatedEvent;
import hust.project.common.event.ProductDeletedEvent;
import hust.project.common.event.ProductDomainEvent;
import hust.project.common.event.ProductUpdatedEvent;
import hust.project.searchservice.entity.dto.request.CreateProductRequest;
import hust.project.searchservice.entity.dto.request.UpdateProductRequest;
import hust.project.searchservice.service.IProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@KafkaListener(topics = "${kafka.topic.product-event}")
@Slf4j
public class ProductEventHandler implements IProductEventConsumer {

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
        log.info("[ProductEventHandler] handle product created event, event: {}", event);

        CreateProductRequest request = CreateProductRequest.builder()
                .id(event.getId())
                .name(event.getName())
                .slug(event.getSlug())
                .price(event.getPrice())
                .isPublished(event.getIsPublished())
                .brand(event.getBrand())
                .categories(event.getCategories())
                .build();

        productService.createProduct(request);
    }

    public void updateProduct(ProductUpdatedEvent event) {
        UpdateProductRequest request = UpdateProductRequest.builder()
                .name(event.getName())
                .slug(event.getSlug())
                .price(event.getPrice())
                .isPublished(event.getIsPublished())
                .brand(event.getBrand())
                .categories(event.getCategories())
                .build();

        productService.updateProduct(event.getId(), request);
    }

    public void deleteProduct(ProductDeletedEvent event) {
        productService.deleteProduct(event.getId());
    }
}
