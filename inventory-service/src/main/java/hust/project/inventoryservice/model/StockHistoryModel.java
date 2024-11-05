package hust.project.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stock_histories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "note")
    private String note;

    @Column(name = "warehouse_id")
    private Long warehouseId;
}
