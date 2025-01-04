package hust.project.orderservice.service.impl;

import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.request.UpdateOrderStatusRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.service.IOrderService;
import hust.project.orderservice.usecase.CancelOrderUseCase;
import hust.project.orderservice.usecase.CreateOrderUseCase;
import hust.project.orderservice.usecase.GetOrderUseCase;
import hust.project.orderservice.usecase.UpdateOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;

    @Override
    public OrderEntity createOrder(CreateOrderRequest request) {
        return createOrderUseCase.createOrder(request);
    }

    @Override
    public OrderEntity getDetailOrder(Long id) {
        return getOrderUseCase.getDetailOrder(id);
    }

    @Override
    public Pair<PageInfo, List<OrderEntity>> getMyOrders(Long userId, GetMyOrderRequest request) {
        return getOrderUseCase.getMyOrders(userId, request);
    }


    @Override
    public Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter) {
        return getOrderUseCase.getAllOrders(filter);
    }

    @Override
    public List<OrderEntity> getOrderExistedByUserIdAndProductIds(Long userId, List<Long> productIds) {
        return getOrderUseCase.getOrderExistedByUserIdAndProductIds(userId, productIds);
    }

    @Override
    public OrderEntity updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        return updateOrderUseCase.updateOrderStatus(id, request);
    }

    @Override
    public void approveOrder(Long orderId) {
        updateOrderUseCase.approveOrder(orderId);
    }

    @Override
    public void rejectOrder(Long orderId) {
        updateOrderUseCase.rejectOrder(orderId);
    }

    @Override
    public void cancelOrderWeb(Long orderId, Long userId) {
        cancelOrderUseCase.cancelOrderWeb(orderId, userId);
    }

}
