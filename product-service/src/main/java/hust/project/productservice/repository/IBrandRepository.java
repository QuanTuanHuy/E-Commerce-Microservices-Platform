package hust.project.productservice.repository;

import hust.project.productservice.model.BrandModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBrandRepository extends IBaseRepository<BrandModel> {
    List<BrandModel> findByIdIn(List<Long> ids);
}
