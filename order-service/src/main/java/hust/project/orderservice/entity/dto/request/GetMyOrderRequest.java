package hust.project.orderservice.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMyOrderRequest {
    private Integer page;
    private Integer pageSize;
    private String productName;
    private List<String> orderStatuses;
}
