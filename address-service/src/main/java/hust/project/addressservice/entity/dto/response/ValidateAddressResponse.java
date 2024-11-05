package hust.project.addressservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateAddressResponse {
    private Boolean isValid;
}
