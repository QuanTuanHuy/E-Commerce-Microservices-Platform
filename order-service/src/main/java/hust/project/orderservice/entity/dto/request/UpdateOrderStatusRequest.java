package hust.project.orderservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {
    private String orderStatus;
}
