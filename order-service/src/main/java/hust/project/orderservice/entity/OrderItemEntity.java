package hust.project.orderservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemEntity {
    private Long id;

    private Long productId;

    private String productName;

    private Double productPrice;

    private Long orderId;

    private Integer quantity;

    private String note;

    private Double discountAmount;
}
