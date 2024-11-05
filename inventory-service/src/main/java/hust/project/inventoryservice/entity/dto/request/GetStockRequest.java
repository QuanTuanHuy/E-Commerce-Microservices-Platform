package hust.project.inventoryservice.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetStockRequest {
    @NotNull
    private Long warehouseId;

    private String productName;
    private String productSlug;
}
