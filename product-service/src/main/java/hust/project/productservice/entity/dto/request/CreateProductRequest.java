package hust.project.productservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProductRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    private String description;

    private Double price;

    private Boolean isPublished;

    @Min(1L)
    private Long stockQuantity;

    private Long brandId;

    private String thumbnailImage;

    private Long parentId;


    private List<Long> categoryIds;

    private List<String> images;

    private List<Long> productRelatedIds;

    private List<CreateProductVariationRequest> productVariants;
}
