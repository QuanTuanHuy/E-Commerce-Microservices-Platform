package hust.project.orderservice.usecase;

import hust.project.common.event.OrderCreatedEvent;
import hust.project.common.event.OrderItem;
import hust.project.orderservice.constant.OrderStatus;
import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.OrderItemEntity;
import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.eventpublisher.OrderEventPublisher;
import hust.project.orderservice.mapper.OrderItemMapper;
import hust.project.orderservice.mapper.OrderMapper;
import hust.project.orderservice.mapper.ShippingAddressMapper;
import hust.project.orderservice.port.*;
import hust.project.orderservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateOrderUseCase {
    private final IOrderPort orderPort;
    private final IOrderItemPort orderItemPort;
    private final IShippingAddressPort shippingAddressPort;
    private final ICartItemPort cartItemPort;
    private final IProductPort productPort;
    private final OrderEventPublisher orderEventPublisher;

    public OrderEntity createOrder(CreateOrderRequest request) {
        OrderEntity order = OrderMapper.INSTANCE.toEntityFromRequest(request);
        order.setCustomerId(AuthenticationUtils.getCurrentUserId());


        ShippingAddressEntity shippingAddress = ShippingAddressMapper.INSTANCE.toEntityFromRequest(
                request.getShippingAddress());
        shippingAddress = shippingAddressPort.save(shippingAddress);
        order.setShippingAddressId(shippingAddress.getId());
        order.setShippingAddress(shippingAddress);

        order.setOrderStatus(OrderStatus.PENDING_CREATED.name());


        order = orderPort.save(order);
        final Long orderId = order.getId();


        List<OrderItemEntity> orderItems = request.getOrderItems().stream()
                .map(oi -> OrderItemMapper.INSTANCE.toEntityFromRequest(orderId, oi))
                .toList();

        order.setOrderItems(orderItemPort.saveAll(orderItems));


        // delete cart items
//        List<DeleteCartItemRequest> deleteCartItemRequests = orderItems.stream()
//                .map(oi -> DeleteCartItemRequest.builder()
//                        .productId(oi.getProductId())
//                        .quantity(oi.getQuantity())
//                        .build())
//                .toList();
//
//        cartItemPort.adjustOrDeleteCartItems(deleteCartItemRequests);
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .customerId(order.getCustomerId())
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .couponCode(order.getCouponCode())
                .discountAmount(order.getDiscountAmount())
                .orderItems(orderItems.stream()
                        .map(oi -> OrderItem.builder()
                                .productId(oi.getProductId())
                                .quantity(oi.getQuantity())
                                .productPrice(oi.getProductPrice())
                                .build())
                        .toList())
                .build();
        orderEventPublisher.publishOrderCreatedEvent(orderCreatedEvent);


        // subtract product quantity
//        List<UpdateProductQuantityRequest> updateProductQuantityRequests = orderItems.stream()
//                .map(oi -> UpdateProductQuantityRequest.builder()
//                        .productId(oi.getProductId())
//                        .quantity(- (long) oi.getQuantity())
//                        .build())
//                .toList();
//
//        productPort.updateProductQuantity(updateProductQuantityRequests);

        return order;
    }

}
