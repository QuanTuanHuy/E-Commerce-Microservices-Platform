package hust.project.promotionservice.entity.dto.request;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetPromotionRequest {
    private Integer page;
    private Integer pageSize;
    private String name;
    private String couponCode;
    private Instant startDate;
    private Instant endDate;
}
