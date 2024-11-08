package hust.project.orderservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShippingAddressRequest {
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
