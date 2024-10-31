package hust.project.cartservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@IdClass(CartItemId.class)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemModel extends AuditTable {
    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Integer quantity;
}
