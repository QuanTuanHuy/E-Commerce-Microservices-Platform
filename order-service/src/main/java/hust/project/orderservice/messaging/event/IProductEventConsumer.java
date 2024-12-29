package hust.project.orderservice.messaging.event;

import hust.project.common.event.ProductDomainEvent;

public interface IProductEventConsumer {
    void handleProductDomainEvent(ProductDomainEvent event);
}
