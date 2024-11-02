package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.CreateProvinceRequest;
import hust.project.addressservice.mapper.ProvinceMapper;
import hust.project.addressservice.port.IProvincePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateProvinceUseCase {
    private final IProvincePort provincePort;

    public ProvinceEntity createProvince(CreateProvinceRequest request) {
        ProvinceEntity province = ProvinceMapper.INSTANCE.toEntityFromRequest(request);
        return provincePort.save(province);
    }
}
