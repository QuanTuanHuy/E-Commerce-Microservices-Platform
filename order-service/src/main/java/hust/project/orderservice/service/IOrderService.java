package hust.project.orderservice.service;

import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.request.UpdateOrderStatusRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IOrderService {
    OrderEntity createOrder(CreateOrderRequest request);

    OrderEntity getDetailOrder(Long id);

    Pair<PageInfo, List<OrderEntity>> getMyOrders(Long userId, GetMyOrderRequest request);

    Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter);

    List<OrderEntity> getOrderExistedByUserIdAndProductIds(Long userId, List<Long> productIds);

    OrderEntity updateOrderStatus(Long id, UpdateOrderStatusRequest request);

    void cancelOrderWeb(Long orderId, Long userId);
}
