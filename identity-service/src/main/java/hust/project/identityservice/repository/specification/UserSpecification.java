package hust.project.identityservice.repository.specification;

import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.model.UserModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class UserSpecification {
    public static Specification<UserModel> getAll(GetUserRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getPhoneNumber() != null) {
                predicates.add(builder.equal(root.get("phoneNumber"), filter.getPhoneNumber()));
            }

            if (filter.getRoleId() != null) {
                predicates.add(builder.equal(root.get("roleId"), filter.getRoleId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
