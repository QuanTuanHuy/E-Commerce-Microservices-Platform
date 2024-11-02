package hust.project.addressservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {
    private Long id;

    private String contactName;

    private String phoneNumber;

    private String addressDetail;

    private Long provinceId;

    private Long districtId;

    private Long wardId;


    private ProvinceEntity province;

    private DistrictEntity district;

    private WardEntity ward;
}
