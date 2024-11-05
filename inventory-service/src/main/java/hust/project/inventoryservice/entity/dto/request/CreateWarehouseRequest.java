package hust.project.inventoryservice.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWarehouseRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;

    @NotNull
    private CreateWarehouseAddressRequest addressRequest;
}
