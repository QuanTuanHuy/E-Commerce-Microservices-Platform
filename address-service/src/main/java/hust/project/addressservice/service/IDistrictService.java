package hust.project.addressservice.service;

import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.CreateDistrictRequest;
import hust.project.addressservice.entity.dto.request.GetDistrictRequest;
import hust.project.addressservice.entity.dto.request.UpdateDistrictRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IDistrictService {
    DistrictEntity createDistrict(CreateDistrictRequest request);

    DistrictEntity getDetailDistrict(Long id);

    Pair<PageInfo, List<DistrictEntity>> getAllDistricts(GetDistrictRequest filter);

    DistrictEntity updateDistrict(Long id, UpdateDistrictRequest request);

    void deleteDistrict(Long id);
}
