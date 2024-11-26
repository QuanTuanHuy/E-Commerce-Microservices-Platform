package hust.project.productservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateProductVariationRequest {
    private Long id;
    private String name;
    private String slug;
    private Double price;
    private String thumbnailImage;

    private List<String> images;
}
