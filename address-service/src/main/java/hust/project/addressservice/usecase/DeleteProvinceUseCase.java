package hust.project.addressservice.usecase;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IProvincePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DeleteProvinceUseCase {
    private final IProvincePort provincePort;
    private final IDistrictPort districtPort;

    public void deleteProvince(Long id) {
        if (!CollectionUtils.isEmpty(districtPort.getDistrictsByProvinceId(id))) {
            log.error("[DeleteProvinceUseCase] province has districts");
            throw new AppException(ErrorCode.DELETE_PROVINCE_FAILED);
        }

        provincePort.deleteProvince(id);
    }
}
