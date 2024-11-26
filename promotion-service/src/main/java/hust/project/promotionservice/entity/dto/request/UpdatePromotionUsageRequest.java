package hust.project.promotionservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdatePromotionUsageRequest {
    private Long productId;
    private Long orderId;
}
