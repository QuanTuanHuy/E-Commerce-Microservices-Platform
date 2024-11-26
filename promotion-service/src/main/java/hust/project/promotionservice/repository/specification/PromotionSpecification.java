package hust.project.promotionservice.repository.specification;

import hust.project.promotionservice.entity.dto.request.GetPromotionRequest;
import hust.project.promotionservice.model.PromotionModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class PromotionSpecification {
    public static Specification<PromotionModel> getAllPromotions(GetPromotionRequest filter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (StringUtils.hasText(filter.getCouponCode())) {
                predicates.add(criteriaBuilder.like(root.get("couponCode"), "%" + filter.getCouponCode() + "%"));
            }

            if (filter.getStartDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("startDate"), filter.getStartDate(), filter.getEndDate()));
            }

            if (filter.getEndDate() != null) {
                predicates.add(criteriaBuilder.between(root.get("endDate"), filter.getStartDate(), filter.getEndDate()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
