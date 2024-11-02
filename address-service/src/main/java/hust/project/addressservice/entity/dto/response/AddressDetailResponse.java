package hust.project.addressservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDetailResponse {
    private Long id;

    private String addressDetail;

    private String contactName;

    private String phoneNumber;

    private Long provinceId;

    private String provinceName;

    private Long districtId;

    private String districtName;

    private Long wardId;

    private String wardName;
}
