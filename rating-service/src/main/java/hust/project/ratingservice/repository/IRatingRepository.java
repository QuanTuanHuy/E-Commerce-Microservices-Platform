package hust.project.ratingservice.repository;

import hust.project.ratingservice.model.RatingModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IRatingRepository extends IBaseRepository<RatingModel> {
    @Query("SELECT AVG(r.ratingStar) FROM RatingModel r WHERE r.productId IN (:productIds)")
    List<Object[]> getAverageRatingByProductIds(List<Long> productIds);

    Optional<RatingModel> findByCustomerIdAndProductId(Long customerId, Long productId);
}
