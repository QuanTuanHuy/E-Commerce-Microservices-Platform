package hust.project.orderservice.repository;

import hust.project.orderservice.model.OrderItemModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderItemRepository extends IBaseRepository<OrderItemModel> {
    List<OrderItemModel> findByOrderId(Long orderId);

    List<OrderItemModel> findByOrderIdIn(List<Long> orderIds);

    void deleteByOrderId(Long orderId);
}
