package hust.project.orderservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderItemRequest {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer quantity;
    private String note;
    private Double discountAmount;
}
