package hust.project.common.event;

import hust.project.common.dto.OrderItem;
import hust.project.common.dto.ShippingAddress;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent implements OrderDomainEvent {
    private Long orderId;
    private Long customerId;
    private Double totalPrice;
    private List<OrderItem> orderItems;
    private ShippingAddress shippingAddress;

}
