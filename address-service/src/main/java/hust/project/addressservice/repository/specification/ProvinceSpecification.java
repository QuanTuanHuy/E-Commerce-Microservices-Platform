package hust.project.addressservice.repository.specification;

import hust.project.addressservice.entity.dto.request.GetProvinceRequest;
import hust.project.addressservice.model.ProvinceModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class ProvinceSpecification {
    public static Specification<ProvinceModel> getAllProvinces(GetProvinceRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
