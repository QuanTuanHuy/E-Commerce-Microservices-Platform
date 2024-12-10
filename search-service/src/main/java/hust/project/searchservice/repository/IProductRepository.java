package hust.project.searchservice.repository;

import hust.project.searchservice.model.ProductModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends ElasticsearchRepository<ProductModel, Long> {
}
