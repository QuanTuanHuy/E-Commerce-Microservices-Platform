package hust.project.productservice.repository;

import hust.project.productservice.entity.dto.request.GetProductRequest;
import hust.project.productservice.entity.dto.response.PageInfo;
import hust.project.productservice.model.ProductModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.RequestContextFilter;

import java.util.List;

@Service
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final RequestContextFilter requestContextFilter;
    @PersistenceContext
    private EntityManager entityManager;

    public CustomProductRepositoryImpl(RequestContextFilter requestContextFilter) {
        this.requestContextFilter = requestContextFilter;
    }

    @Override
    public Pair<PageInfo, List<ProductModel>> getAllProducts(GetProductRequest filter) {
        String sql =
                "SELECT * FROM products\n" +
                "WHERE id IN \n" +
                "(\n" +
                "\tSELECT p.id\n" +
                "    FROM products p\n" +
                "    JOIN product_category pc ON p.id = pc.product_id\n";


        sql += "    WHERE p.is_published = " + filter.getIsPublished() + "\n";

        if (StringUtils.hasText(filter.getProductSlug())) {
            sql += "    AND p.slug = " + filter.getProductSlug() + "\n";
        }

        if (StringUtils.hasText(filter.getProductName())) {
            sql += "    AND p.name LIKE '%" + filter.getProductName() + "%'\n";
        }

        if (filter.getPriceFrom() != null) {
            sql += "    AND p.price >= " + filter.getPriceFrom() + "\n";
        }

        if (filter.getPriceTo() != null) {
            sql += "    AND p.price <= " + filter.getPriceTo() + "\n";
        }

        if (!CollectionUtils.isEmpty(filter.getBrandIds())) {
            List<Long> brandIds = filter.getBrandIds();
            StringBuilder brandIdsStr = new StringBuilder();

            for (int i = 0; i < brandIds.size(); i++) {
                brandIdsStr.append(brandIds.get(i));
                if (i < brandIds.size() - 1) {
                    brandIdsStr.append(", ");
                }
            }

            sql += "    AND p.brand_id IN ( " + brandIdsStr + " )\n";
        }

        if (!CollectionUtils.isEmpty(filter.getCategoryIds())) {
            List<Long> categoryIds = filter.getCategoryIds();
            StringBuilder categoryIdsStr = new StringBuilder();

            for (int i = 0; i < categoryIds.size(); i++) {
                categoryIdsStr.append(categoryIds.get(i));
                if (i < categoryIds.size() - 1) {
                    categoryIdsStr.append(", ");
                }
            }
            sql += "    AND pc.category_id IN ( " + categoryIdsStr + " )\n";
        }

        sql += ")";

        sql += "LIMIT " + filter.getPageSize() + " OFFSET " + filter.getPage() * filter.getPageSize();

        Query query = entityManager.createNativeQuery(sql, ProductModel.class);
        List<ProductModel> products = query.getResultList();

        var pageInfo = PageInfo.builder()
                .pageSize(filter.getPage())
                .totalRecord(filter.getPageSize())
                .build();
        return Pair.of(pageInfo, products);
    }
}
