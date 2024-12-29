package hust.project.productservice.port;

import hust.project.common.event.ProductDomainEvent;

public interface IProductEventPort {
    void publishProductDomainEvent(ProductDomainEvent event);
}
