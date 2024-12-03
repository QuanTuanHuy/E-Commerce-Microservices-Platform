package hust.project.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    private Long productId;
    private Integer quantity;
    private Double productPrice;
}
