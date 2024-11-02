package hust.project.addressservice.repository;

import hust.project.addressservice.model.WardModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWardRepository extends IBaseRepository<WardModel> {
    List<WardModel> findByIdIn(List<Long> ids);

    List<WardModel> findByDistrictId(Long districtId);
}
