package hust.project.orderservice.repository;

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
            List<String> orderStatuses = filter.getOrderStatuses();
            StringBuilder orderStatusesStr = new StringBuilder();
            for (int i = 0; i < orderStatuses.size(); i++) {
                orderStatusesStr.append("'").append(orderStatuses.get(i)).append("'");
                if (i < orderStatuses.size() - 1) {
                    orderStatusesStr.append(", ");
                }
            }

            sql += "    AND o.order_status IN (" + orderStatusesStr + ")\n";
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
}
