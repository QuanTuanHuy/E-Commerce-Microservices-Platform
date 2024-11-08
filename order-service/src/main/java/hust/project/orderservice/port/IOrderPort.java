package hust.project.orderservice.port;

import hust.project.orderservice.entity.OrderEntity;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IOrderPort {
    OrderEntity save(OrderEntity orderEntity);

    Pair<PageInfo, List<OrderEntity>> getAllOrders(GetOrderRequest filter);

    OrderEntity getOrderById(Long id);

    void deleteOrder(Long id);
}
