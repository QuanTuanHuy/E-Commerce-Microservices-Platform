package hust.project.promotionservice.entity.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreatePromotionRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    private String description;

    @NotBlank
    private String couponCode;

    @NotBlank
    private String discountType;

    @NotBlank
    private String usageType;

    @NotBlank
    private String applyTo;

    private Long usageLimit;

    @Max(100)
    private Long discountPercentage;

    private Long discountAmount;

    private Long minimumOrderAmount;

    private Boolean isActive;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;

    private List<Long> productIds;

    private List<Long> categoryIds;

    private List<Long> brandIds;
}
