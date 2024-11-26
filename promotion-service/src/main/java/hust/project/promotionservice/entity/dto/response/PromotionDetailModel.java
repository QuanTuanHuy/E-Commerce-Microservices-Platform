package hust.project.promotionservice.entity.dto.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PromotionDetailModel {
    private Long id;

    private String name;

    private String slug;

    private String description;

    private String couponCode;

    private String discountType;

    private String usageType;

    private String applyTo;

    private Long usageLimit;

    private Long usageCount;

    private Long discountPercentage;

    private Long discountAmount;

    private Long minimumOrderAmount;

    private Boolean isActive;

    private Instant startDate;

    private Instant endDate;

    private List<ProductGetModel> products;

    private List<CategoryGetModel> categories;

    private List<BrandGetModel> brands;
}
