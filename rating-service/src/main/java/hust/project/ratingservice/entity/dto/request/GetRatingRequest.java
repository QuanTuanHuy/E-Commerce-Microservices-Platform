package hust.project.ratingservice.entity.dto.request;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRatingRequest {
    private Integer page;
    private Integer pageSize;
    private List<Long> productIds;
    private Long customerId;
    private Instant createdFrom;
    private Instant createdTo;
}
