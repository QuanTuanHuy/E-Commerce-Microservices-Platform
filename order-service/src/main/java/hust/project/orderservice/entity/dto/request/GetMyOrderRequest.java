package hust.project.orderservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetMyOrderRequest {
    private String productName;
    private String orderStatus;
}
