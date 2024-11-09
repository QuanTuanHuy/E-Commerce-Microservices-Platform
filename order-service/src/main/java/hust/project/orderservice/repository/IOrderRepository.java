package hust.project.orderservice.repository;

import hust.project.orderservice.model.OrderModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends IBaseRepository<OrderModel>, CustomOrderRepository {
    Page<OrderModel> findByCustomerIdAndOrderStatusIn(Long customerId, List<String> orderStatuses, Pageable pageable);

    Page<OrderModel> findByCustomerId(Long customerId, Pageable pageable);
}
