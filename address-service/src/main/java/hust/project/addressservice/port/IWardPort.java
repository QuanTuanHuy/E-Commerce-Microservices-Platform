package hust.project.addressservice.port;

import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.GetWardRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IWardPort {
    WardEntity save(WardEntity wardEntity);

    WardEntity getWardById(Long id);

    Pair<PageInfo, List<WardEntity>> getAllWards(GetWardRequest filter);

    List<WardEntity> getWardsByDistrictId(Long districtId);

    List<WardEntity> getWardsByIds(List<Long> ids);

    void deleteWard(Long id);
}
