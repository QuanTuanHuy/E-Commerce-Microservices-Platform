package hust.project.orderservice.service.impl;

import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.CreateOrderRequest;
import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.request.UpdateOrderStatusRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.service.IOrderService;
import hust.project.orderservice.usecase.CreateOrderUseCase;
import hust.project.orderservice.usecase.GetOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    @Override
    public OrderEntity createOrder(CreateOrderRequest request) {
        return createOrderUseCase.createOrder(request);
    }

    @Override
    public OrderEntity getDetailOrder(Long id) {
        return getOrderUseCase.getDetailOrder(id);
    }

    @Override
    public List<OrderEntity> getMyOrders(GetMyOrderRequest request) {
        return List.of();
    }

    @Override
    public Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter) {
        return getOrderUseCase.getAllOrders(filter);
    }

    @Override
    public OrderEntity updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        return null;
    }

}
