package hust.project.ratingservice.port;

import hust.project.ratingservice.entity.RatingEntity;
import hust.project.ratingservice.entity.dto.request.GetRatingRequest;
import hust.project.ratingservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IRatingPort {
    RatingEntity save(RatingEntity ratingEntity);

    Pair<PageInfo, List<RatingEntity>> getAllRatings(GetRatingRequest filter);

    RatingEntity getRatingById(Long id);

    RatingEntity getRatingByCustomerIdAndProductId(Long customerId, Long productId);

    Double getAverageRatingStarByProductIds(List<Long> productIds);

    void deleteRatingById(Long id);
}
