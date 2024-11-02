package hust.project.addressservice.service;

import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.CreateWardRequest;
import hust.project.addressservice.entity.dto.request.GetWardRequest;
import hust.project.addressservice.entity.dto.request.UpdateWardRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IWardService {
    WardEntity createWard(CreateWardRequest request);

    WardEntity getDetailWard(Long id);

    Pair<PageInfo, List<WardEntity>> getAllWards(GetWardRequest filter);

    WardEntity updateWard(Long id, UpdateWardRequest request);

    void deleteWard(Long id);
}
