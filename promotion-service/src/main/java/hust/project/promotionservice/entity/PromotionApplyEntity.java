package hust.project.promotionservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionApplyEntity {
    private Long id;

    private Long promotionId;

    private Long productId;

    private Long categoryId;

    private Long brandId;
}
