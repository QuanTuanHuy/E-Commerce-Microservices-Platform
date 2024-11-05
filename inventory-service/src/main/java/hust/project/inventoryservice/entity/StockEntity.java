package hust.project.inventoryservice.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockEntity {
    private Long id;

    private Long productId;

    private Long availableQuantity;

    private Long soldQuantity;

    private Long warehouseId;


    private WarehouseEntity warehouse;
}
