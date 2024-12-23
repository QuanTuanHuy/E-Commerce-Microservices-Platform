package hust.project.orderservice.repository;

import hust.project.orderservice.model.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends IBaseRepository<ProductModel> {
    List<ProductModel> findByIdIn(List<Long> ids);
}
