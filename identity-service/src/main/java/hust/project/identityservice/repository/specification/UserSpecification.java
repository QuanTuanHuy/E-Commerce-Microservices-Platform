package hust.project.identityservice.repository.specification;

import hust.project.identityservice.entity.dto.request.GetUserListRequest;
import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.model.UserModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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

    public static Specification<UserModel> getAllUsers(GetUserListRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getPhoneNumber())) {
                predicates.add(builder.like(root.get("phoneNumber"), "%" + filter.getPhoneNumber() + "%"));
            }

            if (StringUtils.hasText(filter.getEmail())) {
                predicates.add(builder.like(root.get("email"), "%" + filter.getEmail() + "%"));
            }

            if (!CollectionUtils.isEmpty(filter.getUserIds())) {
                predicates.add(root.get("id").in(filter.getUserIds()));
            }

            if (!CollectionUtils.isEmpty(filter.getRoleIds())) {
                predicates.add(root.get("roleID").in(filter.getRoleIds()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
