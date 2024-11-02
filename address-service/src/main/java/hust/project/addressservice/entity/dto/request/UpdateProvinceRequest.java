package hust.project.addressservice.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProvinceRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String code;
}
