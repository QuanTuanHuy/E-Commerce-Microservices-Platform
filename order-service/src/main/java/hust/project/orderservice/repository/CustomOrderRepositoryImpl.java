package hust.project.orderservice.repository;

import hust.project.orderservice.entity.dto.request.GetMyOrderRequest;
import hust.project.orderservice.entity.dto.request.GetOrderRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.model.OrderModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomOrderRepositoryImpl implements CustomOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Pair<PageInfo, List<OrderModel>> getAllOrders(GetOrderRequest filter) {
        String sql =
                "SELECT * FROM orders\n" +
                "WHERE id IN\n" +
                "(\n" +
                "\tSELECT o.id FROM orders o\n" +
                "    JOIN order_items oi ON o.id = oi.order_id\n" +
                "    JOIN shipping_addresses sa ON o.shipping_address_id = sa.id\n" +
                "    WHERE 1 = 1\n";

        if (filter.getCreatedFrom() != null && filter.getCreatedTo() != null) {
            sql += "    AND o.created_at BETWEEN '" + filter.getCreatedFrom() + "' AND '" + filter.getCreatedTo() + "'\n";
        }

        if (StringUtils.hasText(filter.getEmail())) {
            sql += "    AND o.email = '" + filter.getEmail() + "'\n";
        }
        if (StringUtils.hasText(filter.getPhoneNumber())) {
            sql += "    AND sa.phone_number = '" + filter.getPhoneNumber() + "'\n";
        }
        if (!CollectionUtils.isEmpty(filter.getOrderStatuses())) {

            sql += "    AND o.order_status IN (" + buildOderStatuses(filter.getOrderStatuses()) + ")\n";
        }

        if (StringUtils.hasText(filter.getProductName())) {
            sql += "    AND oi.product_name LIKE '%" + filter.getProductName() + "%'\n";
        }

        if (StringUtils.hasText(filter.getProvinceName())) {
            sql += "    AND sa.province_name LIKE '%" + filter.getProvinceName() + "%'\n";
        }
        if (StringUtils.hasText(filter.getDistrictName())) {
            sql += "    AND sa.district_name LIKE '%" + filter.getDistrictName() + "%'\n";
        }
        if (StringUtils.hasText(filter.getWardName())) {
            sql += "    AND sa.ward_name LIKE '%" + filter.getWardName() + "%'\n";
        }
        sql += ") ORDER BY created_at DESC\n";

        sql += "LIMIT " + filter.getPageSize() + " OFFSET " + filter.getPage() * filter.getPageSize();

        Query query = entityManager.createNativeQuery(sql, OrderModel.class);

        List<OrderModel> orders = query.getResultList();

        PageInfo pageInfo = PageInfo.builder()
                .pageSize((long) filter.getPageSize())
                .totalRecord((long) orders.size())
                .build();

        return Pair.of(pageInfo, orders);
    }

    @Override
    public Pair<PageInfo, List<OrderModel>> getMyOrders(Long userId, GetMyOrderRequest filter) {
        String sql =
                "WITH T AS\n" +
                "(\n" +
                "\tSELECT id FROM orders WHERE customer_id = " + userId + "\n";

        if (!CollectionUtils.isEmpty(filter.getOrderStatuses())) {
            sql += "    AND order_status IN (" + buildOderStatuses(filter.getOrderStatuses()) + ")\n";
        }
        sql +=  ")\n" +
                "SELECT * FROM orders\n" +
                "WHERE id IN\n" +
                "(\n" +
                "\tSELECT DISTINCT(t.id) FROM T t JOIN order_items oi ON t.id = oi.order_id\n" +
                "    WHERE oi.product_name LIKE '%" + filter.getProductName() +  "%'\n" +
                ")";

        Query query = entityManager.createNativeQuery(sql, OrderModel.class);

        List<OrderModel> orders = query.getResultList();

        PageInfo pageInfo = PageInfo.builder()
                .pageSize((long) filter.getPageSize())
                .totalRecord((long) orders.size())
                .build();

        return Pair.of(pageInfo, orders);

    }

    @Override
    public List<OrderModel> getExistedOrdersByCustomerIdAndProductIds(Long customerId, List<Long> productIds) {
        String sql = """
                    WITH T AS
                    (
                        SELECT * FROM orders WHERE customer_id = :customerId
                        AND order_status = 'FINISHED'
                    )
                    SELECT t.* FROM T AS t
                    JOIN order_items AS oi ON t.id = oi.order_id
                    WHERE oi.product_id IN (:productIds)  
                """;

        Query query = entityManager.createNativeQuery(sql, OrderModel.class);
        query.setParameter("customerId", customerId);
        query.setParameter("productIds", productIds);

        return query.getResultList();
    }


    private StringBuilder buildOderStatuses(List<String> orderStatuses) {
        StringBuilder orderStatusesStr = new StringBuilder();
        for (int i = 0; i < orderStatuses.size(); i++) {
            orderStatusesStr.append("'").append(orderStatuses.get(i)).append("'");
            if (i < orderStatuses.size() - 1) {
                orderStatusesStr.append(", ");
            }
        }
        return orderStatusesStr;
    }
}
