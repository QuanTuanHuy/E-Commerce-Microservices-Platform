package hust.project.orderservice.saga.createorder;

import hust.project.common.dto.OrderItem;
import hust.project.common.dto.ShippingAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "create_order_saga_data")
public class CreateOrderSagaData {
    @Id
    private Long orderId;
    private Long customerId;
    private Long ticketId;
    private Double totalPrice;

    @ElementCollection
    private List<OrderItem> orderItems;

    @Embedded
    private ShippingAddress shippingAddress;
}
