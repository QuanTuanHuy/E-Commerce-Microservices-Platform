package hust.project.orderservice.saga.createorder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreateOrderSagaDataRepository extends JpaRepository<CreateOrderSagaData, Long> {
}
