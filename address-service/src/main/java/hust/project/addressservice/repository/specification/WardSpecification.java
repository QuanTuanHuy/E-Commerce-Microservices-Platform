package hust.project.addressservice.repository.specification;

import hust.project.addressservice.entity.dto.request.GetWardRequest;
import hust.project.addressservice.model.WardModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class WardSpecification {
    public static Specification<WardModel> getAllWards(GetWardRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (filter.getDistrictId() != null) {
                predicates.add(builder.equal(root.get("districtId"), filter.getDistrictId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
