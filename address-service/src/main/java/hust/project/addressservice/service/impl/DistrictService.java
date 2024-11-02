package hust.project.addressservice.service.impl;

import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.CreateDistrictRequest;
import hust.project.addressservice.entity.dto.request.GetDistrictRequest;
import hust.project.addressservice.entity.dto.request.UpdateDistrictRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.service.IDistrictService;
import hust.project.addressservice.usecase.CreateDistrictUseCase;
import hust.project.addressservice.usecase.DeleteDistrictUseCase;
import hust.project.addressservice.usecase.GetDistrictUseCase;
import hust.project.addressservice.usecase.UpdateDistrictUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DistrictService implements IDistrictService {
    private final CreateDistrictUseCase createDistrictUseCase;
    private final GetDistrictUseCase getDistrictUseCase;
    private final UpdateDistrictUseCase updateDistrictUseCase;
    private final DeleteDistrictUseCase deleteDistrictUseCase;

    @Override
    public DistrictEntity createDistrict(CreateDistrictRequest request) {
        return createDistrictUseCase.createDistrict(request);
    }

    @Override
    public DistrictEntity getDetailDistrict(Long id) {
        return getDistrictUseCase.getDetailDistrict(id);
    }

    @Override
    public Pair<PageInfo, List<DistrictEntity>> getAllDistricts(GetDistrictRequest filter) {
        return getDistrictUseCase.getAllDistricts(filter);
    }

    @Override
    public DistrictEntity updateDistrict(Long id, UpdateDistrictRequest request) {
        return updateDistrictUseCase.updateDistrict(id, request);
    }

    @Override
    public void deleteDistrict(Long id) {
        deleteDistrictUseCase.deleteDistrict(id);
    }
}
