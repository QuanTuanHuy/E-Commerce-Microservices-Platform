package hust.project.promotionservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerifyPromotionResult {
    private Boolean isValid;
    private Long productId;
    private String couponCode;
    private String discountType;
    private Long discountValue;
}
