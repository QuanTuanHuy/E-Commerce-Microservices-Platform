package hust.project.productservice.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductListRequest {
    private String name;
    private String slug;
    private List<Long> productIds;
}
