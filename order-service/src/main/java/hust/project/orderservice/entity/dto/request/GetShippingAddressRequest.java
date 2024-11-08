package hust.project.orderservice.entity.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetShippingAddressRequest {
    private Integer page;
    private Integer pageSize;
    private String contactName;
    private String phoneNumber;
    private Long provinceId;
    private Long districtId;
    private Long wardId;
}
