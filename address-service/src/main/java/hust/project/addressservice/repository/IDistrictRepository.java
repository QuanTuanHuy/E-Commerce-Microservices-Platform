package hust.project.addressservice.repository;

import hust.project.addressservice.model.DistrictModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDistrictRepository extends IBaseRepository<DistrictModel> {
    List<DistrictModel> findByIdIn(List<Long> ids);

    List<DistrictModel> findByProvinceId(Long provinceId);
}
