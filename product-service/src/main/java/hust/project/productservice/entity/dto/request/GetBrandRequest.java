package hust.project.productservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBrandRequest {
    private Long page;
    private Long pageSize;
    private String name;
    private String slug;
    private Boolean isPublished;
}
