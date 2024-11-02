package hust.project.addressservice.port;

import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.GetDistrictRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IDistrictPort {
    DistrictEntity save(DistrictEntity districtEntity);

    DistrictEntity getDistrictById(Long id);

    List<DistrictEntity> getDistrictsByIds(List<Long> ids);

    List<DistrictEntity> getDistrictsByProvinceId(Long provinceId);

    Pair<PageInfo, List<DistrictEntity>> getAllDistricts(GetDistrictRequest filter);

    void deleteDistrict(Long id);
}
