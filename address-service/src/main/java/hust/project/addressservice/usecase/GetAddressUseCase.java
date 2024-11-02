package hust.project.addressservice.usecase;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.entity.AddressEntity;
import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.port.IAddressPort;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IProvincePort;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetAddressUseCase {
    private final IAddressPort addressPort;
    private final IProvincePort provincePort;
    private final IDistrictPort districtPort;
    private final IWardPort wardPort;

    public AddressEntity getDetailAddress(Long id) {
        AddressEntity address = addressPort.getAddressById(id);

        address.setProvince(provincePort.getProvinceById(address.getProvinceId()));
        address.setDistrict(districtPort.getDistrictById(address.getDistrictId()));
        address.setWard(wardPort.getWardById(address.getWardId()));

        return address;
    }

    public List<AddressEntity> getAddressByIds(List<Long> ids) {
        List<AddressEntity> addressList = addressPort.getAddressByIds(ids);

        if (addressList.isEmpty()) {
            return addressList;
        }

        if (addressList.size() != ids.size()) {
            log.error("[GetAddressUseCase] get address failed");
            throw new AppException(ErrorCode.GET_ADDRESS_FAILED);
        }

        List<Long> provinceIds = addressList.stream().map(AddressEntity::getProvinceId).distinct().toList();
        List<ProvinceEntity> provinces = provincePort.getProvincesByIds(provinceIds);
        var mapIdToProvince = provinces.stream().collect(Collectors.toMap(ProvinceEntity::getId, Function.identity()));

        List<Long> districtIds = addressList.stream().map(AddressEntity::getDistrictId).distinct().toList();
        List<DistrictEntity> districts = districtPort.getDistrictsByIds(districtIds);
        var mapIdToDistrict = districts.stream().collect(Collectors.toMap(DistrictEntity::getId, Function.identity()));

        List<Long> wardIds = addressList.stream().map(AddressEntity::getWardId).distinct().toList();
        List<WardEntity> wards = wardPort.getWardsByIds(wardIds);
        var mapIdToWard = wards.stream().collect(Collectors.toMap(WardEntity::getId, Function.identity()));

        addressList.forEach(address -> {
            address.setProvince(mapIdToProvince.getOrDefault(address.getProvinceId(), null));
            address.setDistrict(mapIdToDistrict.getOrDefault(address.getDistrictId(), null));
            address.setWard(mapIdToWard.getOrDefault(address.getWardId(), null));
        });

        return addressList;
    }
}
