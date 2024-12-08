package hust.project.productservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateProductVariationRequest {
    private String name;
    private String slug;
    private Double price;
    private String thumbnailImage;
    private Long stockQuantity;

    private List<String> images;
}
