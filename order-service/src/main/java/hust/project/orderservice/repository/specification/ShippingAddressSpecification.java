package hust.project.orderservice.repository.specification;

import hust.project.orderservice.entity.dto.request.GetShippingAddressRequest;
import hust.project.orderservice.model.ShippingAddressModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class ShippingAddressSpecification {
    public static Specification<ShippingAddressModel> getAllShippingAddresses(GetShippingAddressRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getContactName())) {
                predicates.add(builder.like(root.get("contactName"), "%" + filter.getContactName() + "%"));
            }

            if (StringUtils.hasText(filter.getPhoneNumber())) {
                predicates.add(builder.equal(root.get("phoneNumber"), filter.getPhoneNumber()));
            }

            if (filter.getProvinceId() != null) {
                predicates.add(builder.equal(root.get("provinceId"), filter.getProvinceId()));
            }

            if (filter.getDistrictId() != null) {
                predicates.add(builder.equal(root.get("districtId"), filter.getDistrictId()));
            }

            if (filter.getWardId() != null) {
                predicates.add(builder.equal(root.get("wardId"), filter.getWardId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
