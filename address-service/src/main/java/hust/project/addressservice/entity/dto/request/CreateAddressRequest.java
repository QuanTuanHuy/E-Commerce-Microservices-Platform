package hust.project.addressservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressRequest {
    @Size(max = 450)
    private String contactName;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 450)
    private String addressDetail;

    @NotNull @Min(1)
    private Long provinceId;

    @NotNull @Min(1)
    private Long districtId;

    @NotNull @Min(1)
    private Long wardId;
}
