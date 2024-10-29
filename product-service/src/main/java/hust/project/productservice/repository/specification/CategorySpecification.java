package hust.project.productservice.repository.specification;

import hust.project.productservice.entity.dto.request.GetCategoryRequest;
import hust.project.productservice.model.CategoryModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class CategorySpecification {
    public static Specification<CategoryModel> getAllCategories(GetCategoryRequest filter) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(builder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (StringUtils.hasText(filter.getSlug())) {
                predicates.add(builder.equal(root.get("slug"), filter.getSlug()));
            }

            if (filter.getIsPublished() != null) {
                predicates.add(builder.equal(root.get("isPublished"), filter.getIsPublished()));
            }

            if (filter.getParentId() != null) {
                predicates.add(builder.equal(root.get("parentId"), filter.getParentId()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
