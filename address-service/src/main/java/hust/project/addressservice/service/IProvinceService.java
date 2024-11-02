package hust.project.addressservice.service;

import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.CreateProvinceRequest;
import hust.project.addressservice.entity.dto.request.GetProvinceRequest;
import hust.project.addressservice.entity.dto.request.UpdateProvinceRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IProvinceService {
    ProvinceEntity createProvince(CreateProvinceRequest request);

    ProvinceEntity getDetailProvince(Long id);

    ProvinceEntity updateProvince(Long id, UpdateProvinceRequest request);

    Pair<PageInfo, List<ProvinceEntity>> getAllProvinces(GetProvinceRequest filter);

    void deleteProvince(Long id);
}
