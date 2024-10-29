package hust.project.productservice.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBrandRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String slug;

    private String image;
    private Boolean isPublished;
}
