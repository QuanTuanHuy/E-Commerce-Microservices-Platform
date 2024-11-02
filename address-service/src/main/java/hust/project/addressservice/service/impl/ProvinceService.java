package hust.project.addressservice.service.impl;

import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.CreateProvinceRequest;
import hust.project.addressservice.entity.dto.request.GetProvinceRequest;
import hust.project.addressservice.entity.dto.request.UpdateProvinceRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.service.IProvinceService;
import hust.project.addressservice.usecase.CreateProvinceUseCase;
import hust.project.addressservice.usecase.DeleteProvinceUseCase;
import hust.project.addressservice.usecase.GetProvinceUseCase;
import hust.project.addressservice.usecase.UpdateProvinceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvinceService implements IProvinceService {
    private final CreateProvinceUseCase createProvinceUseCase;
    private final GetProvinceUseCase getProvinceUseCase;
    private final UpdateProvinceUseCase updateProvinceUseCase;
    private final DeleteProvinceUseCase deleteProvinceUseCase;

    @Override
    public ProvinceEntity createProvince(CreateProvinceRequest request) {
        return createProvinceUseCase.createProvince(request);
    }

    @Override
    public ProvinceEntity getDetailProvince(Long id) {
        return getProvinceUseCase.getDetailProvince(id);
    }

    @Override
    public ProvinceEntity updateProvince(Long id, UpdateProvinceRequest request) {
        return updateProvinceUseCase.updateProvince(id, request);
    }

    @Override
    public Pair<PageInfo, List<ProvinceEntity>> getAllProvinces(GetProvinceRequest filter) {
        return getProvinceUseCase.getAllProvinces(filter);
    }

    @Override
    public void deleteProvince(Long id) {
        deleteProvinceUseCase.deleteProvince(id);
    }
}
