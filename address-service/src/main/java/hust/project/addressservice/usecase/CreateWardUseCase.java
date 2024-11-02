package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.CreateWardRequest;
import hust.project.addressservice.mapper.WardMapper;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.port.IWardPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateWardUseCase {
    private final IWardPort wardPort;
    private final IDistrictPort districtPort;

    public WardEntity createWard(CreateWardRequest request) {
        districtPort.getDistrictById(request.getDistrictId());

        WardEntity ward = WardMapper.INSTANCE.toEntityFromRequest(request);
        return wardPort.save(ward);
    }
}
