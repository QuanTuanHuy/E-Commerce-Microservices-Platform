package hust.project.searchservice.controller.eventhandler;

import hust.project.common.event.ProductCreatedEvent;
import hust.project.common.event.ProductDeletedEvent;
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
@KafkaListener(topics = "${kafka.topic.product-event}")
@RequiredArgsConstructor
@Slf4j
public class ProductEventHandler {
    private final IProductService productService;

    @KafkaHandler
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
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

    @KafkaHandler
    public void handleProductDeletedEvent(ProductDeletedEvent event) {
        log.info("[ProductEventHandler] handle product deleted event, event: {}", event);

        productService.deleteProduct(event.getId());
    }

    @KafkaHandler
    public void handleProductUpdatedEvent(ProductUpdatedEvent event) {
        log.info("[ProductEventHandler] handle product updated event, event: {}", event);

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
}
