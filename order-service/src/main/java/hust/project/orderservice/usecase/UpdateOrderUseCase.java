package hust.project.orderservice.usecase;

import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.constant.OrderStatus;
import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.UpdateOrderStatusRequest;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.port.IOrderItemPort;
import hust.project.orderservice.port.IOrderPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateOrderUseCase {
    private final IOrderPort orderPort;
    private final IOrderItemPort orderItemPort;
    private final CancelOrderUseCase cancelOrderUseCase;

    @Transactional
    public OrderEntity updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        OrderEntity order = orderPort.getOrderById(id);

        if (order.getOrderStatus().equals(OrderStatus.CANCELLED.name())) {
            log.error("[UpdateOrderUseCase] order is cancelled, id: {}", id);
            throw new AppException(ErrorCode.UPDATE_ORDER_FAILED);
        }

        if (request.getOrderStatus().equals(OrderStatus.CANCELLED.name())) {
            cancelOrderUseCase.cancelOrder(id, order);
            return null;
        }

        if (!isStateValid(request.getOrderStatus(), order.getOrderStatus())) {
            throw new AppException(ErrorCode.UPDATE_ORDER_FAILED);
        }

        order.setOrderStatus(OrderStatus.valueOf(request.getOrderStatus()).name());
        order = orderPort.save(order);


        order.setOrderItems(orderItemPort.getOrderItemsByOrderId(id));
        return order;
    }

    public void approveOrder(Long orderId) {
        OrderEntity order = orderPort.getOrderById(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.APPROVED_PENDING.name())) {
            log.error("[UpdateOrderUseCase] order is not in pending status, id: {}", orderId);
            throw new AppException(ErrorCode.UPDATE_ORDER_FAILED);
        }

        order.setOrderStatus(OrderStatus.APPROVED.name());
        orderPort.save(order);
    }

    public void rejectOrder(Long orderId) {
        OrderEntity order = orderPort.getOrderById(orderId);
        if (!order.getOrderStatus().equals(OrderStatus.APPROVED_PENDING.name())) {
            log.error("[UpdateOrderUseCase] order is not in pending status, id: {}", orderId);
            throw new AppException(ErrorCode.UPDATE_ORDER_FAILED);
        }

        order.setOrderStatus(OrderStatus.REJECTED.name());
        orderPort.save(order);
    }


    private Boolean isStateValid(String newStatus, String currentStatus) {
        return (OrderStatus.APPROVED_PENDING.name().equals(currentStatus) && OrderStatus.APPROVED.name().equals(newStatus)) ||
                (OrderStatus.APPROVED.name().equals(currentStatus) && OrderStatus.SHIPPING.name().equals(newStatus)) ||
                (OrderStatus.APPROVED.name().equals(currentStatus) && OrderStatus.PAID.name().equals(newStatus)) ||
                (OrderStatus.PAID.name().equals(currentStatus) && OrderStatus.SHIPPING.name().equals(newStatus)) ||
                (OrderStatus.SHIPPING.name().equals(currentStatus) && OrderStatus.DELIVERED.name().equals(newStatus)) ||
                (OrderStatus.DELIVERED.name().equals(currentStatus) && OrderStatus.PAID.name().equals(newStatus)) ||
                (OrderStatus.PAID.name().equals(currentStatus) && OrderStatus.FINISHED.name().equals(newStatus));
    }
}
