package hust.project.orderservice.usecase;

import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.constant.OrderStatus;
import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.OrderItemEntity;
import hust.project.orderservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.port.IOrderItemPort;
import hust.project.orderservice.port.IOrderPort;
import hust.project.orderservice.port.IProductPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CancelOrderUseCase {
    private final IOrderPort orderPort;
    private final IOrderItemPort orderItemPort;
    private final IProductPort productPort;

    public void cancelOrderOps(Long orderId) {
        cancelOrder(orderId, null);
    }

    public void cancelOrderWeb(Long orderId, Long userId) {
        OrderEntity order = orderPort.getOrderById(orderId);

        if (order.getOrderStatus().equals(OrderStatus.CANCELLED.name())) {
            log.error("[CancelOrderUseCase] order is cancelled, id: {}", orderId);
            throw new AppException(ErrorCode.CANCEL_ORDER_FAILED);
        }

        if (order.getCustomerId().equals(userId)) {
            cancelOrder(orderId, order);
        } else {
            log.error("[CancelOrderUseCase] cancel order failed, order id: {}, user id: {}", orderId, userId);
            throw new AppException(ErrorCode.CANCEL_ORDER_FAILED);
        }
    }

    public void cancelOrder(Long id, OrderEntity order) {
        if (order == null) {
            order = orderPort.getOrderById(id);
        }

        List<OrderItemEntity> orderItems = orderItemPort.getOrderItemsByOrderId(id);

        // increase product quantity
        List<UpdateProductQuantityRequest> productQuantityRequests = orderItems.stream()
                .map(oi -> UpdateProductQuantityRequest.builder()
                        .productId(oi.getProductId())
                        .quantity((long) oi.getQuantity())
                        .build())
                .toList();

        productPort.updateProductQuantity(productQuantityRequests);

//        orderItemPort.deleteOrderItemsByOrderId(id);
//        shippingAddressPort.deleteShippingAddress(order.getShippingAddressId());

        order.setOrderStatus(OrderStatus.CANCELLED.name());
        orderPort.save(order);
    }
}
