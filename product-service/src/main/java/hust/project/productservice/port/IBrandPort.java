package hust.project.productservice.port;

import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.dto.request.GetBrandRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IBrandPort {
    BrandEntity save(BrandEntity brandEntity);

    BrandEntity getBrandById(Long id);

    List<BrandEntity> getBrandsByIds(List<Long> ids);

    Pair<PageInfo, List<BrandEntity>> getAllBrands(GetBrandRequest filter);

    void deleteBrand(Long id);
}
