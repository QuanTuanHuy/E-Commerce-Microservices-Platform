package hust.project.inventoryservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateWarehouseAddressRequest {
    @Size(max = 450)
    private String contactName;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 450)
    private String addressDetail;

    @NotNull @Min(1)
    private Long provinceId;

    private String provinceName;

    @NotNull @Min(1)
    private Long districtId;

    private String districtName;

    @NotNull @Min(1)
    private Long wardId;

    private String wardName;
}