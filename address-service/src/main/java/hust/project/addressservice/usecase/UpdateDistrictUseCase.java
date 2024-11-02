package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.UpdateDistrictRequest;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IProvincePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UpdateDistrictUseCase {
    private final IDistrictPort districtPort;
    private final IProvincePort provincePort;

    public DistrictEntity updateDistrict(Long id, UpdateDistrictRequest request) {
        DistrictEntity district = districtPort.getDistrictById(id);

        if (!district.getProvinceId().equals(request.getProvinceId())) {
            provincePort.getProvinceById(request.getProvinceId());

            district.setProvinceId(request.getProvinceId());
        }

        district.setName(request.getName());
        district.setCode(request.getCode());

        return districtPort.save(district);
    }

}
