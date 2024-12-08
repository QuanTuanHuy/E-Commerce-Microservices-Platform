package hust.project.ratingservice.usecase;

import hust.project.ratingservice.constant.ErrorCode;
import hust.project.ratingservice.entity.RatingEntity;
import hust.project.ratingservice.entity.dto.request.CreateRatingRequest;
import hust.project.ratingservice.entity.dto.response.ProductGetModel;
import hust.project.ratingservice.entity.dto.response.UserInfoResponse;
import hust.project.ratingservice.exception.AppException;
import hust.project.ratingservice.port.IOrderPort;
import hust.project.ratingservice.port.IProductPort;
import hust.project.ratingservice.port.IRatingPort;
import hust.project.ratingservice.port.IUserPort;
import hust.project.ratingservice.utils.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CreateRatingUseCase {
    private final IRatingPort ratingPort;
    private final IProductPort productPort;
    private final IOrderPort orderPort;
    private final IUserPort userPort;

    public RatingEntity createRating(CreateRatingRequest request) {
        Long userId = AuthenticationUtils.getCurrentUserId();

        RatingEntity existedRating = ratingPort.getRatingByCustomerIdAndProductId(userId, request.getProductId());
        if (existedRating != null) {
            log.error("[CreateRatingUseCase] Rating existed, userId: {}, productId: {}", userId, request.getProductId());
            throw new AppException(ErrorCode.CREATE_RATING_FAILED);
        }

        List<Long> productIds = productPort.getProductVariants(request.getProductId()).stream()
                .map(ProductGetModel::getId)
                .toList();

        if (!CollectionUtils.isEmpty(productIds)) {
            productIds.add(request.getProductId());
        } else {
            productIds = List.of(request.getProductId());
        }

        if (!orderPort.checkOrderExistedByUserId(productIds)) {
            log.error("[CreateRatingUseCase] user has not bought this product, userId: {}, productId: {}",
                    userId, request.getProductId());
            throw new AppException(ErrorCode.CREATE_RATING_FAILED);
        }


        UserInfoResponse userInfo = userPort.getUserInfoById(userId);

        RatingEntity rating = RatingEntity.builder()
                .customerId(userId)
                .customerName(userInfo.getName())
                .productId(request.getProductId())
                .productName(request.getProductName())
                .content(request.getContent())
                .ratingStar(request.getRatingStar())
                .build();

        return ratingPort.save(rating);
    }
}
