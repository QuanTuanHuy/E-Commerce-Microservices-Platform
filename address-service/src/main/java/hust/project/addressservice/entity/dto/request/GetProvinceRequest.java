package hust.project.addressservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProvinceRequest {
    private Integer page;
    private Integer pageSize;
    private String name;
}
