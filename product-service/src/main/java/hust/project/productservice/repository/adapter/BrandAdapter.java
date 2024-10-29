package hust.project.productservice.repository.adapter;

import hust.project.productservice.constants.ErrorCode;
import hust.project.productservice.entity.BrandEntity;
import hust.project.productservice.entity.dto.request.GetBrandRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.exception.AppException;
import hust.project.productservice.mapper.BrandMapper;
import hust.project.productservice.model.BrandModel;
import hust.project.productservice.port.IBrandPort;
import hust.project.productservice.repository.IBrandRepository;
import hust.project.productservice.repository.specification.BrandSpecification;
import hust.project.productservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandAdapter implements IBrandPort {
    private final IBrandRepository brandRepository;

    @Override
    public BrandEntity save(BrandEntity brandEntity) {
        try {
            BrandModel brandModel = BrandMapper.INSTANCE.toModelFromEntity(brandEntity);
            return BrandMapper.INSTANCE.toEntityFromModel(brandRepository.save(brandModel));
        } catch (Exception e) {
            log.error("[BrandAdapter] save brand failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_BRAND_FAILED);
        }
    }

    @Override
    public BrandEntity getBrandById(Long id) {
        return BrandMapper.INSTANCE.toEntityFromModel(brandRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[BrandAdapter] get brand failed, id: {}", id);
                    return new AppException(ErrorCode.GET_BRAND_FAILED);
                }
        ));
    }

    @Override
    public List<BrandEntity> getBrandsByIds(List<Long> ids) {
        return BrandMapper.INSTANCE.toEntitiesFromModels(brandRepository.findByIdIn(ids));
    }

    @Override
    public Pair<PageInfo, List<BrandEntity>> getAllBrands(GetBrandRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()), Math.toIntExact(filter.getPageSize()),
                Sort.by("id").descending());

        var result = brandRepository.findAll(BrandSpecification.getAllBrands(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, BrandMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public void deleteBrand(Long id) {
        try {
            brandRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[BrandAdapter] delete brand failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_BRAND_FAILED);
        }
    }
}
