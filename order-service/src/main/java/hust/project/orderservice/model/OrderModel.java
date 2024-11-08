package hust.project.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "shipping_address_id")
    private Long shippingAddressId;

    @Column(name = "email")
    private String email;

    @Column(name = "note")
    private String note;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "discount_amount")
    private Double discountAmount;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    private String paymentStatus;
}
