package hust.project.searchservice.repository.adapter;

import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.util.ObjectBuilder;
import hust.project.searchservice.constant.ErrorCode;
import hust.project.searchservice.constant.SortType;
import hust.project.searchservice.entity.ProductEntity;
import hust.project.searchservice.entity.dto.request.GetProductRequest;
import hust.project.searchservice.entity.dto.response.PageInfo;
import hust.project.searchservice.exception.AppException;
import hust.project.searchservice.mapper.ProductMapper;
import hust.project.searchservice.model.ProductModel;
import hust.project.searchservice.port.IProductPort;
import hust.project.searchservice.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductAdapter implements IProductPort {
    private final IProductRepository productRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public ProductEntity save(ProductEntity productEntity) {
        try {
            ProductModel productModel = ProductMapper.INSTANCE.toModelFromEntity(productEntity);
            return ProductMapper.INSTANCE.toEntityFromModel(productRepository.save(productModel));
        } catch (Exception e) {
            log.error("[ProductAdapter] save product failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_PRODUCT_FAILED);
        }
    }

    @Override
    public ProductEntity getProductById(Long id) {
        return ProductMapper.INSTANCE.toEntityFromModel(productRepository.findById(id).orElseThrow(() -> {
            log.error("[ProductAdapter] get product by id failed, id: {}", id);
            return new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }));
    }

    @Override
    public Pair<PageInfo, List<ProductEntity>> getAllProducts(GetProductRequest filter) {
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getPageSize());

        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(q -> q
                        .bool( b -> b
                                .should(s -> s
                                        .multiMatch(m -> m
                                                .fields("name", "brand", "categories")
                                                .query(filter.getKeyword())
                                                .fuzziness("1")
                                        )
                                )
                        )
                )
                .withFilter(f -> f
                        .bool(b -> {
                            extractRange(filter.getMinPrice(), filter.getMaxPrice(), b);
                            extractTermsFilter(filter.getBrand(), "brand", b);
                            extractTermsFilter(filter.getCategory(), "categories", b);
                            b.must(m -> m
                                    .term(t -> t
                                            .field("isPublished")
                                            .value(true)
                                    )
                            );
                            return b;
                        })
                )
                .withPageable(pageable)
                .withSort(s -> getSort(filter.getSortType()))
                .build();

        SearchHits<ProductModel> searchHitsResult = elasticsearchOperations.search(nativeQuery, ProductModel.class);
        List<ProductEntity> products = searchHitsResult.getSearchHits().stream()
                .map(SearchHit::getContent)
                .map(ProductMapper.INSTANCE::toEntityFromModel)
                .toList();


        SearchPage<ProductModel> searchPage = SearchHitSupport.searchPageFor(searchHitsResult, pageable);

        var pageInfo = PageInfo.builder()
                .pageSize((long) searchPage.getSize())
                .totalPage((long) searchPage.getTotalPages())
                .totalRecord(searchPage.getTotalElements())
                .previousPage(searchPage.isFirst() ? null : (long) searchPage.getNumber() - 1)
                .nextPage(searchPage.isLast() ? null : (long) searchPage.getNumber() + 1)
                .build();

        return Pair.of(pageInfo, products);
    }

    private ObjectBuilder<SortOptions> getSort(String sortType) {
        FieldSort fieldSort;
        if (sortType.equals(SortType.PRICE_ASC.name())) {
            fieldSort = new FieldSort.Builder()
                    .field("price")
                    .order(SortOrder.Asc)
                    .build();
        } else if (sortType.equals(SortType.PRICE_DESC.name())) {
            fieldSort = new FieldSort.Builder()
                    .field("price")
                    .order(SortOrder.Desc)
                    .build();
        } else {
            fieldSort = new FieldSort.Builder()
                    .field("createdAt")
                    .order(SortOrder.Desc)
                    .missing("_last")
                    .build();
        }
        return new SortOptions.Builder().field(fieldSort);
    }

    private void extractRange(Number min, Number max, BoolQuery.Builder bool) {
        if (min != null || max != null) {
            bool.must(m -> m
                    .range(r -> r
                            .field("price")
                            .gte(min != null ? JsonData.of(min.toString()) : null)
                            .lte(max != null ? JsonData.of(max.toString()) : null)
                    ));
        }
    }

    private void extractTermsFilter(String fieldValues, String productField, BoolQuery.Builder bool) {
        if (!StringUtils.hasText(fieldValues)) {
            return;
        }
        String[] fieldValuesArray = fieldValues.split(",");
        bool.must(m -> {
            BoolQuery.Builder innerBool = new BoolQuery.Builder();
            for (String value : fieldValuesArray) {
                innerBool.should(s -> s
                        .term(t -> t
                                .field(productField)
                                .value(value)
                                .caseInsensitive(true)
                        )
                );
            }
            return new Query.Builder().bool(innerBool.build());
        });
    }

    @Override
    public void deleteProductById(Long id) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[ProductAdapter] delete product failed, error: {}", e.getMessage());
            throw new AppException(ErrorCode.DELETE_PRODUCT_FAILED);
        }
    }
}
