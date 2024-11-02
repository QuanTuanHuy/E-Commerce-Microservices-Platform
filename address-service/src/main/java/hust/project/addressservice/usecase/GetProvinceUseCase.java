package hust.project.addressservice.usecase;

import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.GetProvinceRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.port.IProvincePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetProvinceUseCase {
    private final IProvincePort provincePort;

    public ProvinceEntity getDetailProvince(Long id) {
        return provincePort.getProvinceById(id);
    }

    public Pair<PageInfo, List<ProvinceEntity>> getAllProvinces(GetProvinceRequest filter) {
        return provincePort.getAllProvinces(filter);
    }

}
