package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.UpdateWardRequest;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateWardUseCase {
    private final IWardPort wardPort;
    private final IDistrictPort districtPort;

    @Transactional
    public WardEntity updateWard(Long id, UpdateWardRequest request) {
        WardEntity ward = wardPort.getWardById(id);

        if (!request.getDistrictId().equals(ward.getDistrictId())) {
            districtPort.getDistrictById(request.getDistrictId());
            ward.setDistrictId(request.getDistrictId());
        }

        ward.setName(request.getName());
        ward.setCode(request.getCode());

        return wardPort.save(ward);
    }
}
