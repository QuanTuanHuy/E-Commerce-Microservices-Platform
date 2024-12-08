package hust.project.ratingservice.service;

import hust.project.ratingservice.entity.RatingEntity;
import hust.project.ratingservice.entity.dto.request.CreateRatingRequest;
import hust.project.ratingservice.entity.dto.request.GetRatingRequest;
import hust.project.ratingservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IRatingService {
    RatingEntity createRating(CreateRatingRequest request);

    Pair<PageInfo, List<RatingEntity>> getAllRatings(GetRatingRequest filter);

    Double getAverageRatingStar(List<Long> productIds);
}
