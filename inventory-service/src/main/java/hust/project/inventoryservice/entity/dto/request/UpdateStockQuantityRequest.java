package hust.project.inventoryservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStockQuantityRequest {
    @NotNull @Min(1)
    private Long stockId;
    @NotNull @Min(1)
    private Long quantity;
    private String note;
}
