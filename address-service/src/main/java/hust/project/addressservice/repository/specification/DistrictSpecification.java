package hust.project.addressservice.repository.specification;

import hust.project.addressservice.entity.dto.request.GetDistrictRequest;
import hust.project.addressservice.model.DistrictModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class DistrictSpecification {
    public static Specification<DistrictModel> getAllDistricts(GetDistrictRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (filter.getProvinceId() != null) {
                predicates.add(builder.equal(root.get("provinceId"), filter.getProvinceId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
