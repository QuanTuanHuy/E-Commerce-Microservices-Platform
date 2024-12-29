package hust.project.searchservice.messaging;

import hust.project.common.event.ProductDomainEvent;

public interface IProductEventConsumer {
    void handleProductDomainEvent(ProductDomainEvent event);
}
