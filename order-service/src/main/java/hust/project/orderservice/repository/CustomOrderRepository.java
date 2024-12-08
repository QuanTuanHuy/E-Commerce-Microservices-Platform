package hust.project.orderservice.repository;

import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.model.OrderModel;
import org.springframework.data.util.Pair;

import java.util.List;

public interface CustomOrderRepository {
    Pair<PageInfo, List<OrderModel>> getAllOrders(GetOrderRequest filter);

    Pair<PageInfo, List<OrderModel>> getMyOrders(Long userId, GetMyOrderRequest filter);

    List<OrderModel> getExistedOrdersByCustomerIdAndProductIds(Long customerId, List<Long> productIds);
}
