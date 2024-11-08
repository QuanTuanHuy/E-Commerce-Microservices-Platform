package hust.project.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "note")
    private String note;

    @Column(name = "discount_amount")
    private Double discountAmount;
}
