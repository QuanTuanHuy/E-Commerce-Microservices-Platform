package hust.project.productservice.port;

import hust.project.common.event.ProductCreatedEvent;
import hust.project.common.event.ProductDeletedEvent;
import hust.project.common.event.ProductUpdatedEvent;

public interface IProductEventPort {
    void sendProductCreatedEvent(ProductCreatedEvent productCreatedEvent);

    void sendProductDeletedEvent(ProductDeletedEvent productDeletedEvent);

    void sendProductUpdatedEvent(ProductUpdatedEvent productUpdatedEvent);
}
