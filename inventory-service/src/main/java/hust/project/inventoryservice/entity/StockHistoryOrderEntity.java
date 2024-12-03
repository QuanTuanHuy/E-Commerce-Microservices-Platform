package hust.project.inventoryservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryOrderEntity {
    private Long id;
    private Long orderId;
    private Long stockId;
    private Integer soldQuantity;

    private StockEntity stock;
}
