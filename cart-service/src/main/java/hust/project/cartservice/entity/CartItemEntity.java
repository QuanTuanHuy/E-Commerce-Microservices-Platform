package hust.project.cartservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemEntity {
    private Long customerId;
    private Long productId;
    private Integer quantity;
}
