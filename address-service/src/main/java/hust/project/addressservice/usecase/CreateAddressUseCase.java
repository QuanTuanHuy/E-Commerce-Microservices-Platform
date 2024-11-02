package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.AddressEntity;
import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.CreateAddressRequest;
import hust.project.addressservice.mapper.AddressMapper;
import hust.project.addressservice.port.IAddressPort;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IProvincePort;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateAddressUseCase {
    private final IAddressPort addressPort;
    private final IProvincePort provincePort;
    private final IDistrictPort districtPort;
    private final IWardPort wardPort;

    public AddressEntity createAddress(CreateAddressRequest request) {
        // check if province, district, ward exist
        ProvinceEntity province = provincePort.getProvinceById(request.getProvinceId());
        DistrictEntity district = districtPort.getDistrictById(request.getDistrictId());
        WardEntity ward = wardPort.getWardById(request.getWardId());

        AddressEntity address = AddressMapper.INSTANCE.toEntityFromRequest(request);
        AddressEntity savedAddress = addressPort.save(address);

        savedAddress.setProvince(province);
        savedAddress.setDistrict(district);
        savedAddress.setWard(ward);

        return savedAddress;
    }

}
