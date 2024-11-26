package hust.project.productservice.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UpdateProductRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    private String description;

    private Double price;

    private Boolean isPublished;

    private Long brandId;

    private String thumbnailImage;


    private Set<Long> categoryIds;

    private Set<String> images;

    private Set<Long> relatedProductIds;

    private List<UpdateProductVariationRequest> productVariants;
}
