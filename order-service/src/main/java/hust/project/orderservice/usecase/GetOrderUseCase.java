package hust.project.orderservice.usecase;

import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.OrderItemEntity;
import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.port.IOrderItemPort;
import hust.project.orderservice.port.IOrderPort;
import hust.project.orderservice.port.IShippingAddressPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetOrderUseCase {
    private final IOrderPort orderPort;
    private final IOrderItemPort orderItemPort;
    private final IShippingAddressPort shippingAddressPort;

    public OrderEntity getDetailOrder(Long id) {
        OrderEntity order = orderPort.getOrderById(id);
        order.setOrderItems(orderItemPort.getOrderItemsByOrderId(id));
        order.setShippingAddress(shippingAddressPort.getShippingAddressById(order.getShippingAddressId()));

        return order;
    }

    public Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter) {
        var result = orderPort.getAllOrders(filter);
        var orders = result.getSecond();


        setOrderItemsAndShippingAddress(orders);


        return Pair.of(result.getFirst(), orders);
    }

    public Pair<PageInfo, List<OrderEntity>> getMyOrders(Long userId, GetMyOrderRequest request) {
        var result = orderPort.getMyOrders(userId, request);
        var orders = result.getSecond();


        setOrderItemsAndShippingAddress(orders);


        return Pair.of(result.getFirst(), orders);
    }

    private void setOrderItemsAndShippingAddress(List<OrderEntity> orders) {
        List<Long> orderIds = orders.stream().map(OrderEntity::getId).toList();
        List<OrderItemEntity> orderItems = orderItemPort.getOrderItemsByOrderIds(orderIds);


        List<Long> shippingAddressIds = orders.stream().map(OrderEntity::getShippingAddressId).toList();
        List<ShippingAddressEntity> shippingAddresses = shippingAddressPort.getShippingAddressesByIds(shippingAddressIds);
        var mapIdToShippingAddress = shippingAddresses.stream()
                .collect(Collectors.toMap(ShippingAddressEntity::getId, Function.identity()));


        orders.forEach(order -> {
            order.setOrderItems(orderItems.stream()
                    .filter(orderItem -> orderItem.getOrderId().equals(order.getId()))
                    .toList());
            order.setShippingAddress(mapIdToShippingAddress.getOrDefault(order.getShippingAddressId(), null));
        });
    }

}
