package hust.project.ratingservice.repository.adapter;

import hust.project.ratingservice.constant.ErrorCode;
import hust.project.ratingservice.entity.RatingEntity;
import hust.project.ratingservice.entity.dto.request.GetRatingRequest;
import hust.project.ratingservice.entity.dto.response.PageInfo;
import hust.project.ratingservice.exception.AppException;
import hust.project.ratingservice.mapper.RatingMapper;
import hust.project.ratingservice.model.RatingModel;
import hust.project.ratingservice.port.IRatingPort;
import hust.project.ratingservice.repository.IRatingRepository;
import hust.project.ratingservice.repository.specification.RatingSpecification;
import hust.project.ratingservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RatingAdapter implements IRatingPort {
    private final IRatingRepository ratingRepository;

    @Override
    public RatingEntity save(RatingEntity ratingEntity) {
        try {
            RatingModel model = RatingMapper.INSTANCE.toModelFromEntity(ratingEntity);
            return RatingMapper.INSTANCE.toEntityFromModel(ratingRepository.save(model));
        } catch (Exception e) {
            log.error("[RatingAdapter] save rating failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_RATING_FAILED);
        }
    }

    @Override
    public Pair<PageInfo, List<RatingEntity>> getAllRatings(GetRatingRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize(), Sort.by("id").descending());

        var result = ratingRepository.findAll(RatingSpecification.getAllRatings(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(result);

        return Pair.of(pageInfo, result.getContent().stream()
                .map(RatingMapper.INSTANCE::toEntityFromModel).toList());
    }

    @Override
    public RatingEntity getRatingById(Long id) {
        return RatingMapper.INSTANCE.toEntityFromModel(ratingRepository.findById(id).orElse(null));
    }

    @Override
    public RatingEntity getRatingByCustomerIdAndProductId(Long customerId, Long productId) {
        return RatingMapper.INSTANCE.toEntityFromModel(ratingRepository
                .findByCustomerIdAndProductId(customerId, productId).orElse(null));
    }

    @Override
    public Double getAverageRatingStarByProductIds(List<Long> productIds) {
        List<Object[]> result = ratingRepository.getAverageRatingByProductIds(productIds);
        if (ObjectUtils.isEmpty(result) || result.get(0) == null || result.get(0).length == 0) {
            return 0.0;
        } else {
            return Double.parseDouble(result.get(0)[0].toString());
        }
    }

    @Override
    public void deleteRatingById(Long id) {
        try {
            ratingRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[RatingAdapter] delete rating failed, err: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_RATING_FAILED);
        }
    }
}
