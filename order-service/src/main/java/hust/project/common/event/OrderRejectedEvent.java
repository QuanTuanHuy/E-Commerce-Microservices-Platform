package hust.project.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRejectedEvent {
    private Long orderId;
}
