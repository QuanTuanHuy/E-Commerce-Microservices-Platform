package hust.project.inventoryservice.entity;

import hust.project.inventoryservice.entity.dto.response.UserInfoResponse;
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

    private Long createdById;

    private WarehouseEntity warehouse;

    private UserInfoResponse createdBy;
}
