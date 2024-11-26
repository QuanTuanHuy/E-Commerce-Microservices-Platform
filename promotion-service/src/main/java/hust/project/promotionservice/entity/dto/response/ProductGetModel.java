package hust.project.promotionservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductGetModel {
    private Long id;
    private String name;
    private String slug;
    private Double price;
    private Boolean isPublished;
    private Long stockQuantity;
    private String thumbnailImage;
}
