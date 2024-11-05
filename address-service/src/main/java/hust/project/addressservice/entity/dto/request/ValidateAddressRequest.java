package hust.project.addressservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateAddressRequest {
    @NotNull
    @Min(1)
    private Long provinceId;
    @NotBlank
    private String provinceName;
    @NotNull @Min(1)
    private Long districtId;
    @NotBlank
    private String districtName;
    @NotNull @Min(1)
    private Long wardId;
    @NotBlank
    private String wardName;
}
