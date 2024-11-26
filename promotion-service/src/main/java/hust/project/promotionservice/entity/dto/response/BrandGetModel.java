package hust.project.promotionservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandGetModel {
    private Long id;
    private String name;
    private String slug;
    private Long imageId;
    private Boolean isPublished;
}
