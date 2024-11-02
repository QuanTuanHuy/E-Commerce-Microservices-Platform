package hust.project.addressservice.repository.adapter;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.entity.ProvinceEntity;
import hust.project.addressservice.entity.dto.request.GetProvinceRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.mapper.ProvinceMapper;
import hust.project.addressservice.model.ProvinceModel;
import hust.project.addressservice.port.IProvincePort;
import hust.project.addressservice.repository.IProvinceRepository;
import hust.project.addressservice.repository.specification.ProvinceSpecification;
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
public class ProvinceAdapter implements IProvincePort {
    private final IProvinceRepository provinceRepository;

    @Override
    public ProvinceEntity save(ProvinceEntity provinceEntity) {
        try {
            ProvinceModel provinceModel = ProvinceMapper.INSTANCE.toModelFromEntity(provinceEntity);
            return ProvinceMapper.INSTANCE.toEntityFromModel(provinceRepository.save(provinceModel));
        } catch (Exception e) {
            log.error("[ProvinceAdapter] save province failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PROVINCE_FAILED);
        }
    }

    @Override
    public ProvinceEntity getProvinceById(Long id) {
        return ProvinceMapper.INSTANCE.toEntityFromModel(provinceRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[ProvinceAdapter] get province by id failed, id: {}", id);
                    return new AppException(ErrorCode.GET_PROVINCE_FAILED);
                }
        ));
    }

    @Override
    public List<ProvinceEntity> getProvincesByIds(List<Long> ids) {
        return ProvinceMapper.INSTANCE.toEntitiesFromModels(provinceRepository.findByIdIn(ids));
    }

    @Override
    public Pair<PageInfo, List<ProvinceEntity>> getAllProvinces(GetProvinceRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by("name").ascending());

        var result = provinceRepository.findAll(ProvinceSpecification.getAllProvinces(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, ProvinceMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public void deleteProvince(Long id) {
        try {
            provinceRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[ProvinceAdapter] delete province failed, id: {}", id);
            throw new AppException(ErrorCode.DELETE_PROVINCE_FAILED);
        }
    }
}
