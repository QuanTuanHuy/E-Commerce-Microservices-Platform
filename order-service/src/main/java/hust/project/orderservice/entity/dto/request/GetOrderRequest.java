package hust.project.orderservice.entity.dto.request;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetOrderRequest {
    private Integer page;
    private Integer pageSize;
    private List<String> orderStatuses;
    private Instant createdFrom;
    private Instant createdTo;
    private String productName;
    private String email;
    private String phoneNumber;
    private String provinceName;
    private String districtName;
    private String wardName;
}
