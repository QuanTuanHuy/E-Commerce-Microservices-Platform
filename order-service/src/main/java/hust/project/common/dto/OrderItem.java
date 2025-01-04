package hust.project.common.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class OrderItem {
    private Long productId;
    private Double productPrice;
    private Integer quantity;
}