package hust.project.productservice.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NotBlank
@AllArgsConstructor
@Builder
public class GetProductRequest {
    private Long page;
    private Long pageSize;
    private String productName;
    private String productSlug;

    private Double priceFrom;
    private Double priceTo;

    private List<Long> brandIds;
    private List<Long> categoryIds;

    private Boolean isPublished;
}
