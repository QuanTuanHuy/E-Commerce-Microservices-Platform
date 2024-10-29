package hust.project.productservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandEntity {
    private Long id;
    private String name;
    private String slug;
    private Long imageId;
    private Boolean isPublished;

    private ImageEntity image;
}
