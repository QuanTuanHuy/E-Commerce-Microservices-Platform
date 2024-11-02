package hust.project.addressservice.usecase;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteDistrictUseCase {
    private final IDistrictPort districtPort;
    private final IWardPort wardPort;

    public void deleteDistrict(Long id) {
        if (!CollectionUtils.isEmpty(wardPort.getWardsByDistrictId(id))) {
            throw new AppException(ErrorCode.DELETE_DISTRICT_FAILED);
        }

        districtPort.deleteDistrict(id);
    }
}
