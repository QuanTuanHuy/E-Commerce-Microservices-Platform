package hust.project.inventoryservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStockHistoryRequest {
    private Integer page;
    private Integer pageSize;
    private Long productId;
    private Long warehouseId;
}
