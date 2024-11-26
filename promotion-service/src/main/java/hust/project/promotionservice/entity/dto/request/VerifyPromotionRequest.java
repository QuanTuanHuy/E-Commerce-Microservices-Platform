package hust.project.promotionservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class VerifyPromotionRequest {
    private String couponCode;
    private Long orderPrice;
    private Set<Long> productIds;
}
