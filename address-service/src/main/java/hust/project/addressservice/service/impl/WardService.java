package hust.project.addressservice.service.impl;

import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.CreateWardRequest;
import hust.project.addressservice.entity.dto.request.GetWardRequest;
import hust.project.addressservice.entity.dto.request.UpdateWardRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.service.IWardService;
import hust.project.addressservice.usecase.CreateWardUseCase;
import hust.project.addressservice.usecase.DeleteWardUseCase;
import hust.project.addressservice.usecase.GetWardUseCase;
import hust.project.addressservice.usecase.UpdateWardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WardService implements IWardService {
    private final CreateWardUseCase createWardUseCase;
    private final GetWardUseCase getWardUseCase;
    private final UpdateWardUseCase updateWardUseCase;
    private final DeleteWardUseCase deleteWardUseCase;

    @Override
    public WardEntity createWard(CreateWardRequest request) {
        return createWardUseCase.createWard(request);
    }

    @Override
    public WardEntity getDetailWard(Long id) {
        return getWardUseCase.getDetailWard(id);
    }

    @Override
    public Pair<PageInfo, List<WardEntity>> getAllWards(GetWardRequest filter) {
        return getWardUseCase.getAllWards(filter);
    }

    @Override
    public WardEntity updateWard(Long id, UpdateWardRequest request) {
        return updateWardUseCase.updateWard(id, request);
    }

    @Override
    public void deleteWard(Long id) {
        deleteWardUseCase.deleteWard(id);
    }
}
