package hust.project.productservice.repository;

import hust.project.productservice.model.CategoryModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends IBaseRepository<CategoryModel> {
    List<CategoryModel> findByIdIn(List<Long> ids);
}
