package hust.project.orderservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingAddressEntity {
    private Long id;

    private String contactName;

    private String phoneNumber;

    private String addressDetail;

    private Long provinceId;

    private String provinceName;

    private Long districtId;

    private String districtName;

    private Long wardId;

    private String wardName;
}
