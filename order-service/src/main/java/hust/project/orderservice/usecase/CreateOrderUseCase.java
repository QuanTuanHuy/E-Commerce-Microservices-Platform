package hust.project.orderservice.usecase;

import hust.project.common.event.OrderDomainEvent;
import hust.project.orderservice.constant.ErrorCode;
import hust.project.orderservice.constant.OrderStatus;
import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.OrderItemEntity;
import hust.project.orderservice.entity.ProductEntity;
import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.CreateOrderItemRequest;
import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.exception.AppException;
import hust.project.orderservice.mapper.OrderItemMapper;
import hust.project.orderservice.mapper.OrderMapper;
import hust.project.orderservice.mapper.ShippingAddressMapper;
import hust.project.orderservice.port.IOrderDomainEventPublisher;
import hust.project.orderservice.port.*;
import hust.project.orderservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class CreateOrderUseCase {
    private final IOrderPort orderPort;
    private final IOrderItemPort orderItemPort;
    private final IShippingAddressPort shippingAddressPort;
//    private final ICartItemPort cartItemPort;
    private final IOrderDomainEventPublisher orderDomainEventPublisher;
    private final IProductPort productPort;

    public OrderEntity createOrder(CreateOrderRequest request) {
        // check product exist
        List<Long> productIds = request.getOrderItems().stream()
                .map(CreateOrderItemRequest::getProductId)
                .distinct().toList();

        if (productIds.size() != request.getOrderItems().size()) {
            log.error("[CreateProductUseCase] Duplicate product id in order items");
            throw new AppException(ErrorCode.CREATE_ORDER_FAILED);
        }

        List<ProductEntity> products = productPort.getProductByIds(productIds).stream()
                .filter(p -> p.getIsPublished().equals(Boolean.TRUE))
                .toList();

        if (products.size() != productIds.size()) {
            log.error("[CreateProductUseCase] Product not found");
            throw new AppException(ErrorCode.CREATE_ORDER_FAILED);
        }

        // create order
        OrderEntity order = OrderMapper.INSTANCE.toEntityFromRequest(request);
        order.setCustomerId(AuthenticationUtils.getCurrentUserId());


        ShippingAddressEntity shippingAddress = ShippingAddressMapper.INSTANCE.toEntityFromRequest(
                request.getShippingAddress());
        shippingAddress = shippingAddressPort.save(shippingAddress);
        order.setShippingAddressId(shippingAddress.getId());
        order.setOrderStatus(OrderStatus.APPROVED_PENDING.name());


        order = orderPort.save(order);
        final Long orderId = order.getId();


        List<OrderItemEntity> orderItems = request.getOrderItems().stream()
                .map(oi -> OrderItemMapper.INSTANCE.toEntityFromRequest(orderId, oi))
                .toList();

        order.setOrderItems(orderItemPort.saveAll(orderItems));
        order.setShippingAddress(shippingAddress);


        // delete cart items
//        List<DeleteCartItemRequest> deleteCartItemRequests = orderItems.stream()
//                .map(oi -> DeleteCartItemRequest.builder()
//                        .productId(oi.getProductId())
//                        .quantity(oi.getQuantity())
//                        .build())
//                .toList();
//
//        cartItemPort.adjustOrDeleteCartItems(deleteCartItemRequests);


        // subtract product quantity
//        List<UpdateProductQuantityRequest> updateProductQuantityRequests = orderItems.stream()
//                .map(oi -> UpdateProductQuantityRequest.builder()
//                        .productId(oi.getProductId())
//                        .quantity(- (long) oi.getQuantity())
//                        .build())
//                .toList();
//
//        productPort.updateProductQuantity(updateProductQuantityRequests);
        OrderDomainEvent orderCreatedEvent = OrderEntity.toOrderCreatedEvent(order);
        orderDomainEventPublisher.publishOrderDomainEvent(orderCreatedEvent);


        return order;
    }

}
