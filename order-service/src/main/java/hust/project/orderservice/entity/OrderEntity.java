package hust.project.orderservice.entity;

import hust.project.common.dto.OrderItem;
import hust.project.common.dto.ShippingAddress;
import hust.project.common.event.OrderCreatedEvent;
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

    public static OrderCreatedEvent toOrderCreatedEvent(OrderEntity order) {
        ShippingAddress shippingAddress = ShippingAddress.builder()
                .contactName(order.getShippingAddress().getContactName())
                .phoneNumber(order.getShippingAddress().getPhoneNumber())
                .addressDetail(order.getShippingAddress().getAddressDetail())
                .provinceId(order.getShippingAddress().getProvinceId())
                .provinceName(order.getShippingAddress().getProvinceName())
                .districtId(order.getShippingAddress().getDistrictId())
                .districtName(order.getShippingAddress().getDistrictName())
                .wardId(order.getShippingAddress().getWardId())
                .wardName(order.getShippingAddress().getWardName())
                .build();

        List<OrderItem> orderItems = order.getOrderItems().stream()
                .map(oi -> OrderItem.builder()
                        .productId(oi.getProductId())
                        .productPrice(oi.getProductPrice())
                        .quantity(oi.getQuantity())
                        .build())
                .toList();

        return OrderCreatedEvent.builder()
                .orderId(order.getId())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .orderItems(orderItems)
                .shippingAddress(shippingAddress)
                .build();
    }
}
