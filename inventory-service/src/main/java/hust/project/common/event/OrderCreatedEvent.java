package hust.project.common.event;

import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private Double totalPrice;
    private String couponCode;
    private Double discountAmount;

    private List<OrderItem> orderItems;
}
