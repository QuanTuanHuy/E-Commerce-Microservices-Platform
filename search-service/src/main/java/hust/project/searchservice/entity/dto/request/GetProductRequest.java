package hust.project.searchservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductRequest {
    private Integer page;
    private Integer pageSize;

    private String keyword;
    private String brand;
    private String category;
    private Double minPrice;
    private Double maxPrice;
    private String sortType;
}
