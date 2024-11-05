package hust.project.productservice.repository.specification;

import hust.project.productservice.entity.dto.request.GetProductListRequest;
import hust.project.productservice.model.ProductModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

public class ProductSpecification {
    public static Specification<ProductModel> getAllProducts(GetProductListRequest filter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (StringUtils.hasText(filter.getName())) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
            }

            if (StringUtils.hasText(filter.getSlug())) {
                predicates.add(criteriaBuilder.equal(root.get("slug"), filter.getSlug()));
            }

            if (!CollectionUtils.isEmpty(filter.getProductIds())) {
                predicates.add(root.get("id").in(filter.getProductIds()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
