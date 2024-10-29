package hust.project.productservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {
    private Long id;

    private String name;

    private String description;

    private String slug;

    private Boolean isPublished;

    private Long parentId;

    private Long imageId;

    private ImageEntity image;
}
