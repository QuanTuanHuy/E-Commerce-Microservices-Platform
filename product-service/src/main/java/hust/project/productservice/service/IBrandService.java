package hust.project.productservice.service;

import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.dto.request.CreateBrandRequest;
import hust.project.productservice.entity.dto.request.GetBrandRequest;
import hust.project.productservice.entity.dto.request.UpdateBrandRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IBrandService {
    BrandEntity createBrand(CreateBrandRequest request);

    BrandEntity getDetailBrand(Long id);

    Pair<PageInfo, List<BrandEntity>> getAllBrands(GetBrandRequest filter);

    BrandEntity updateBrand(Long id, UpdateBrandRequest request);

    void deleteBrand(Long id);
}
