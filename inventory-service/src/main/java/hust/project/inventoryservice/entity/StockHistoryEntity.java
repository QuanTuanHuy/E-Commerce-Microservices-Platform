package hust.project.inventoryservice.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockHistoryEntity {
    private Long id;

    private Long productId;

    private Long quantity;

    private String note;

    private Long warehouseId;

    private WarehouseEntity warehouse;
}
