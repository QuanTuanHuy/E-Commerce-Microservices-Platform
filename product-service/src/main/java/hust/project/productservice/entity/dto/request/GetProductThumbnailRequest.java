package hust.project.productservice.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductThumbnailRequest {
    private List<Long> productIds;
}
