package hust.project.ratingservice.repository.specification;

import hust.project.ratingservice.entity.dto.request.GetRatingRequest;
import hust.project.ratingservice.model.RatingModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

public class RatingSpecification {
    public static Specification<RatingModel> getAllRatings(GetRatingRequest filter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getCustomerId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("customerId"), filter.getCustomerId()));
            }

            if (filter.getCreatedFrom() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreatedFrom()));
            }

            if (filter.getCreatedTo() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), filter.getCreatedTo()));
            }

            if (!CollectionUtils.isEmpty(filter.getProductIds())) {
                predicates.add(root.get("productId").in(filter.getProductIds()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
