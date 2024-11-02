package hust.project.addressservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetWardRequest {
    private Integer page;
    private Integer pageSize;
    private String name;
    @Min(1)
    private Long districtId;
}
