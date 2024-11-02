package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.AddressEntity;
import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.UpdateAddressRequest;
import hust.project.addressservice.port.IAddressPort;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IProvincePort;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateAddressUseCase {
    private final IAddressPort addressPort;
    private final IProvincePort provincePort;
    private final IDistrictPort districtPort;
    private final IWardPort wardPort;


    public AddressEntity updateAddress(Long id, UpdateAddressRequest request) {
        AddressEntity address = addressPort.getAddressById(id);

        if (!request.getProvinceId().equals(address.getProvinceId())) {
            ProvinceEntity province = provincePort.getProvinceById(request.getProvinceId());

            address.setProvinceId(request.getProvinceId());
            address.setProvince(province);
        }

        if (!request.getDistrictId().equals(address.getDistrictId())) {
            DistrictEntity district = districtPort.getDistrictById(request.getDistrictId());

            address.setDistrictId(request.getDistrictId());
            address.setDistrict(district);
        }

        if (!request.getWardId().equals(address.getWardId())) {
            WardEntity ward = wardPort.getWardById(request.getWardId());

            address.setWardId(request.getWardId());
            address.setWard(ward);
        }

        address.setContactName(request.getContactName());
        address.setPhoneNumber(request.getPhoneNumber());
        address.setAddressDetail(request.getAddressDetail());

        return addressPort.save(address);
    }
}
