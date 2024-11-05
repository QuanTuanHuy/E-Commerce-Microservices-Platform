package hust.project.productservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductThumbnailResponse {
    private Long id;
    private String name;
    private String slug;
    private Double price;
    private String thumbnailImage;
}
