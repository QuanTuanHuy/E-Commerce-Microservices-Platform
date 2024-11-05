package hust.project.inventoryservice.repository;

import hust.project.inventoryservice.entity.dto.request.GetWarehouseRequest;
import hust.project.inventoryservice.model.WarehouseModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomWarehouseRepositoryImpl implements CustomWarehouseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<WarehouseModel> getAllWarehouses(GetWarehouseRequest filter) {
        String sql =
                "SELECT wh.id, wh.name, wh.code, wh.address_id, wh.created_at, wh.updated_at\n" +
                "FROM warehouses wh JOIN warehouse_address wa ON wh.address_id = wa.id\n" +
                "WHERE 1 = 1\n";

        if (StringUtils.hasText(filter.getName())) {
            sql += " AND wh.name LIKE '%" + filter.getName() + "%'\n";
        }
        if (StringUtils.hasText(filter.getProvinceName())) {
            sql += " AND wa.province_name LIKE '%" + filter.getProvinceName() + "%'\n";
        }
        if (StringUtils.hasText(filter.getDistrictName())) {
            sql += " AND wa.district_name LIKE '%" + filter.getDistrictName() + "%'\n";
        }
        if (StringUtils.hasText(filter.getWardName())) {
            sql += " AND wa.ward_name LIKE '%" + filter.getWardName() + "%'\n";
        }

        sql += " LIMIT " + filter.getPageSize() + " OFFSET " + filter.getPage() * filter.getPageSize() + ";\n";

        Query query = entityManager.createNativeQuery(sql, WarehouseModel.class);

        return query.getResultList();
    }
}
