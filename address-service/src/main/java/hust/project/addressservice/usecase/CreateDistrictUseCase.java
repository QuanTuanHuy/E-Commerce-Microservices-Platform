package hust.project.addressservice.usecase;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.CreateDistrictRequest;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.mapper.DistrictMapper;
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
public class CreateDistrictUseCase {
    private final IDistrictPort districtPort;
    private final IProvincePort provincePort;

    public DistrictEntity createDistrict(CreateDistrictRequest request) {
        try {
            provincePort.getProvinceById(request.getProvinceId());
        } catch (Exception e) {
            log.error("[CreateDistrictUseCase] province not found");
            throw new AppException(ErrorCode.CREATE_DISTRICT_FAILED);
        }

        DistrictEntity district = DistrictMapper.INSTANCE.toEntityFromRequest(request);
        return districtPort.save(district);
    }

}
