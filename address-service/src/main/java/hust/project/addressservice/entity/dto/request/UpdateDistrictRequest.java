package hust.project.addressservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDistrictRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
    @NotNull @Min(1)
    private Long provinceId;
}
