package hust.project.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_history_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryOrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "product_id")
    private Integer soldQuantity;
}
