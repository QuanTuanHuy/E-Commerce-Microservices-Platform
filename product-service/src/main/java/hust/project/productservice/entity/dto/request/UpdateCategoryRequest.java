package hust.project.productservice.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {
    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String slug;

    private Boolean isPublished;

    private Long parentId;

    private String image;
}
