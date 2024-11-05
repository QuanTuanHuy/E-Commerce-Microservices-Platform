package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.ValidateAddressRequest;
import hust.project.addressservice.entity.dto.response.ValidateAddressResponse;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IProvincePort;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateAddressUseCase {
    private final IProvincePort provincePort;
    private final IDistrictPort districtPort;
    private final IWardPort wardPort;

    public ValidateAddressResponse validateAddress(ValidateAddressRequest request) {
        ValidateAddressResponse response = new ValidateAddressResponse();
        try {
            ProvinceEntity province = provincePort.getProvinceById(request.getProvinceId());
            DistrictEntity district = districtPort.getDistrictById(request.getDistrictId());
            WardEntity ward = wardPort.getWardById(request.getWardId());

            if (province.getName().equals(request.getProvinceName()) &&
                    district.getName().equals(request.getDistrictName()) &&
                    ward.getName().equals(request.getWardName()) &&
                    district.getProvinceId().equals(request.getProvinceId()) &&
                    ward.getDistrictId().equals(request.getDistrictId())) {
                response.setIsValid(true);
            } else {
                response.setIsValid(false);
            }
        } catch (Exception e) {
            response.setIsValid(false);
        }

        return response;
    }
}
