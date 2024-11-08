package hust.project.orderservice.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {
    private Long id;

    private Long customerId;

    private Long shippingAddressId;

    private String email;

    private String note;

    private String couponCode;

    private Double discountAmount;

    private Double totalPrice;

    private Long numberOfItems;

    private String orderStatus;

    private String paymentMethod;

    private String paymentStatus;


    private List<OrderItemEntity> orderItems;

    private ShippingAddressEntity shippingAddress;
}
