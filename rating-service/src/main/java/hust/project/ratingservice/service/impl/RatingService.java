package hust.project.ratingservice.service.impl;

import hust.project.ratingservice.entity.RatingEntity;
import hust.project.ratingservice.entity.dto.request.CreateRatingRequest;
import hust.project.ratingservice.entity.dto.request.GetRatingRequest;
import hust.project.ratingservice.entity.dto.response.PageInfo;
import hust.project.ratingservice.service.IRatingService;
import hust.project.ratingservice.usecase.CreateRatingUseCase;
import hust.project.ratingservice.usecase.GetRatingUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService implements IRatingService {
    private final CreateRatingUseCase createRatingUseCase;
    private final GetRatingUseCase getRatingUseCase;

    @Override
    public RatingEntity createRating(CreateRatingRequest request) {
        return createRatingUseCase.createRating(request);
    }

    @Override
    public Pair<PageInfo, List<RatingEntity>> getAllRatings(GetRatingRequest filter) {
        return getRatingUseCase.getAllRatings(filter);
    }

    @Override
    public Double getAverageRatingStar(List<Long> productIds) {
        return getRatingUseCase.getAverageRatingStar(productIds);
    }
}
