package hust.project.orderservice.repository;

import hust.project.orderservice.model.OrderModel;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends IBaseRepository<OrderModel>, CustomOrderRepository {
}
