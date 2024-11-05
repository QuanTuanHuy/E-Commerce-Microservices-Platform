package hust.project.inventoryservice.entity.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateWarehouseRequest {
    private String name;
    private String code;

    @NotNull
    private UpdateWarehouseAddressRequest addressRequest;
}
