package hust.project.addressservice.repository.adapter;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.entity.DistrictEntity;
import hust.project.addressservice.entity.dto.request.GetDistrictRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.mapper.DistrictMapper;
import hust.project.addressservice.model.DistrictModel;
import hust.project.addressservice.port.IDistrictPort;
import hust.project.addressservice.repository.IDistrictRepository;
import hust.project.addressservice.repository.specification.DistrictSpecification;
import hust.project.addressservice.utils.PageInfoUtils;
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
public class DistrictAdapter implements IDistrictPort {
    private final IDistrictRepository districtRepository;

    @Override
    public DistrictEntity save(DistrictEntity districtEntity) {
        try {
            DistrictModel districtModel = DistrictMapper.INSTANCE.toModelFromEntity(districtEntity);
            return DistrictMapper.INSTANCE.toEntityFromModel(districtRepository.save(districtModel));
        } catch (Exception e) {
            log.error("[DistrictAdapter] save district failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_DISTRICT_FAILED);
        }
    }

    @Override
    public DistrictEntity getDistrictById(Long id) {
        return DistrictMapper.INSTANCE.toEntityFromModel(districtRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[DistrictAdapter] get district by id failed, id: {}", id);
                    return new AppException(ErrorCode.GET_DISTRICT_FAILED);
                }
        ));
    }

    @Override
    public List<DistrictEntity> getDistrictsByIds(List<Long> ids) {
        return DistrictMapper.INSTANCE.toEntitiesFromModels(districtRepository.findByIdIn(ids));
    }

    @Override
    public List<DistrictEntity> getDistrictsByProvinceId(Long provinceId) {
        return DistrictMapper.INSTANCE.toEntitiesFromModels(districtRepository.findByProvinceId(provinceId));
    }

    @Override
    public Pair<PageInfo, List<DistrictEntity>> getAllDistricts(GetDistrictRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by("name").ascending());

        var result = districtRepository.findAll(DistrictSpecification.getAllDistricts(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, DistrictMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public void deleteDistrict(Long id) {
        try {
            districtRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[DistrictAdapter] delete district failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_DISTRICT_FAILED);
        }
    }
}
