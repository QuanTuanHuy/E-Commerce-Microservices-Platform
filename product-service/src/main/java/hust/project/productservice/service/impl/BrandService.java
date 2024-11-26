package hust.project.productservice.service.impl;

import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.dto.request.CreateBrandRequest;
import hust.project.productservice.entity.dto.request.GetBrandRequest;
import hust.project.productservice.entity.dto.request.UpdateBrandRequest;
import hust.project.productservice.entity.dto.response.BrandGetModel;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.service.IBrandService;
import hust.project.productservice.usecase.CreateBrandUseCase;
import hust.project.productservice.usecase.DeleteBrandUseCase;
import hust.project.productservice.usecase.GetBrandUseCase;
import hust.project.productservice.usecase.UpdateBrandUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService implements IBrandService {
    private final CreateBrandUseCase createBrandUseCase;
    private final GetBrandUseCase getBrandUseCase;
    private final UpdateBrandUseCase updateBrandUseCase;
    private final DeleteBrandUseCase deleteBrandUseCase;

    @Override
    public BrandEntity createBrand(CreateBrandRequest request) {
        return createBrandUseCase.createBrand(request);
    }

    @Override
    public BrandEntity getDetailBrand(Long id) {
        return getBrandUseCase.getDetailBrand(id);
    }

    @Override
    public Pair<PageInfo, List<BrandEntity>> getAllBrands(GetBrandRequest filter) {
        return getBrandUseCase.getAllBrands(filter);
    }

    @Override
    public BrandEntity updateBrand(Long id, UpdateBrandRequest request) {
        return updateBrandUseCase.updateBrand(id, request);
    }

    @Override
    public List<BrandGetModel> getBrandsByIds(List<Long> ids) {
        return getBrandUseCase.getBrandsByIds(ids);
    }

    @Override
    public void deleteBrand(Long id) {
        deleteBrandUseCase.deleteBrand(id);
    }
}
