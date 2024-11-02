package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.UpdateProvinceRequest;
import hust.project.addressservice.port.IProvincePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateProvinceUseCase {
    private final IProvincePort provincePort;

    public ProvinceEntity updateProvince(Long id, UpdateProvinceRequest request) {
        ProvinceEntity province = provincePort.getProvinceById(id);

        province.setName(request.getName());
        province.setCode(request.getCode());

        return provincePort.save(province);
    }

}
