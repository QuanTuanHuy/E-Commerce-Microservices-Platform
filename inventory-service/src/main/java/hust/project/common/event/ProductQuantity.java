package hust.project.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductQuantity {
    private Long productId;
    private Long quantity;
}
