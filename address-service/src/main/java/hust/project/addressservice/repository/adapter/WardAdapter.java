package hust.project.addressservice.repository.adapter;

import hust.project.addressservice.constants.ErrorCode;
import hust.project.addressservice.entity.WardEntity;
import hust.project.addressservice.entity.dto.request.GetWardRequest;
import hust.project.addressservice.entity.dto.response.PageInfo;
import hust.project.addressservice.exception.AppException;
import hust.project.addressservice.mapper.WardMapper;
import hust.project.addressservice.model.WardModel;
import hust.project.addressservice.port.IWardPort;
import hust.project.addressservice.repository.IWardRepository;
import hust.project.addressservice.repository.specification.WardSpecification;
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
public class WardAdapter implements IWardPort {
    private final IWardRepository wardRepository;

    @Override
    public WardEntity save(WardEntity wardEntity) {
        try {
            WardModel wardModel = WardMapper.INSTANCE.toModelFromEntity(wardEntity);
            return WardMapper.INSTANCE.toEntityFromModel(wardRepository.save(wardModel));
        } catch (Exception e) {
            log.error("[WardAdapter] save ward failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_WARD_FAILED);
        }
    }

    @Override
    public WardEntity getWardById(Long id) {
        return WardMapper.INSTANCE.toEntityFromModel(wardRepository.findById(id).orElseThrow(
                () -> {
                    log.error("[WardAdapter] get ward by id failed, id: {}", id);
                    return new AppException(ErrorCode.GET_WARD_FAILED);
                }
        ));
    }

    @Override
    public Pair<PageInfo, List<WardEntity>> getAllWards(GetWardRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by("name").ascending());

        var result = wardRepository.findAll(WardSpecification.getAllWards(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, WardMapper.INSTANCE.toEntitiesFromModels(result.getContent()));
    }

    @Override
    public List<WardEntity> getWardsByDistrictId(Long districtId) {
        return WardMapper.INSTANCE.toEntitiesFromModels(wardRepository.findByDistrictId(districtId));
    }

    @Override
    public List<WardEntity> getWardsByIds(List<Long> ids) {
        return WardMapper.INSTANCE.toEntitiesFromModels(wardRepository.findByIdIn(ids));
    }

    @Override
    public void deleteWard(Long id) {
        try {
            wardRepository.deleteById(id);
        } catch (Exception e) {
            throw new AppException(ErrorCode.DELETE_WARD_FAILED);
        }
    }
}
