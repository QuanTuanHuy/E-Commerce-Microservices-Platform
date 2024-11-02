package hust.project.addressservice.port;

import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.GetProvinceRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProvincePort {
    ProvinceEntity save(ProvinceEntity provinceEntity);

    ProvinceEntity getProvinceById(Long id);

    List<ProvinceEntity> getProvincesByIds(List<Long> ids);

    Pair<PageInfo, List<ProvinceEntity>> getAllProvinces(GetProvinceRequest filter);

    void deleteProvince(Long id);
}
