package hust.project.productservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryGetModel {
    private Long id;
    private String name;
    private String description;
    private String slug;
    private Boolean isPublished;
    private Long parentId;
    private Long imageId;
}
