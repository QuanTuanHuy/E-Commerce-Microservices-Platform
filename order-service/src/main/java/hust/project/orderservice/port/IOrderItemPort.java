package hust.project.orderservice.port;

import hust.project.orderservice.entity.OrderItemEntity;

import java.util.List;

public interface IOrderItemPort {
    List<OrderItemEntity> saveAll(List<OrderItemEntity> orderItemEntities);

    List<OrderItemEntity> getOrderItemsByOrderId(Long orderId);

    List<OrderItemEntity> getOrderItemsByOrderIds(List<Long> orderIds);

//    void deleteOrderItemsByOrderId(Long orderId);
}
