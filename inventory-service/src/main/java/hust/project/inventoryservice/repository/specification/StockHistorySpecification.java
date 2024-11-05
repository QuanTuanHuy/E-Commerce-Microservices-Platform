package hust.project.inventoryservice.repository.specification;

import hust.project.inventoryservice.entity.dto.request.GetStockHistoryRequest;
import hust.project.inventoryservice.model.StockHistoryModel;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class StockHistorySpecification {
    public static Specification<StockHistoryModel> getAllStockHistories(GetStockHistoryRequest filter) {
        return (root, query, criteriaBuilder) -> {
            var predicates = new ArrayList<Predicate>();

            if (filter.getProductId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("productId"), filter.getProductId()));
            }

            if (filter.getWarehouseId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("warehouseId"), filter.getWarehouseId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
