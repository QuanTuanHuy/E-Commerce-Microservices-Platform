package hust.project.orderservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductQuantityRequest {
    private Long productId;
    private Long quantity;
}