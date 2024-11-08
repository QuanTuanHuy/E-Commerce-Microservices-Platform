package hust.project.orderservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderRequest {
    private String email;
    private String note;
    private String couponCode;
    private Double discountAmount;
    private Long numberOfItems;
    private Double totalPrice;
    private String paymentMethod;
    private String paymentStatus;

    private CreateShippingAddressRequest shippingAddress;
    private List<CreateOrderItemRequest> orderItems;
}
