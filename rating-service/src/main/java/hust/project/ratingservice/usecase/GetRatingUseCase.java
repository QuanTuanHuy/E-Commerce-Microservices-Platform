package hust.project.ratingservice.usecase;

import hust.project.ratingservice.entity.RatingEntity;
import hust.project.ratingservice.entity.dto.request.GetRatingRequest;
import hust.project.ratingservice.entity.dto.response.PageInfo;
import hust.project.ratingservice.port.IRatingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRatingUseCase {
    private final IRatingPort ratingPort;

    public Pair<PageInfo, List<RatingEntity>> getAllRatings(GetRatingRequest filter) {
        return ratingPort.getAllRatings(filter);
    }

    public Double getAverageRatingStar(List<Long> productIds) {
        return ratingPort.getAverageRatingStarByProductIds(productIds);
    }
}
