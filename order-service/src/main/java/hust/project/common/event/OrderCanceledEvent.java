package hust.project.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCanceledEvent {
    private Long orderId;
    private Long customerId;
    private Double totalPrice;
}
