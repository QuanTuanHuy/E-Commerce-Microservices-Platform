package hust.project.orderservice.port;

import hust.project.common.event.OrderDomainEvent;

public interface IOrderDomainEventPublisher {
    void publishOrderDomainEvent(OrderDomainEvent event);
}
