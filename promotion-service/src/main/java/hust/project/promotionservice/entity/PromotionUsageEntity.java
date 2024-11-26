package hust.project.promotionservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionUsageEntity {
    private Long id;

    private Long promotionId;

    private Long productId;

    private Long userId;

    private Long orderId;
}
