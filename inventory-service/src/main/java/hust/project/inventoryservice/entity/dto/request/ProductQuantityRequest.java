package hust.project.inventoryservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuantityRequest {
    private Long productId;
    private Integer quantity;
}
