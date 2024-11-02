package hust.project.addressservice.repository;

import hust.project.addressservice.model.ProvinceModel;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProvinceRepository extends IBaseRepository<ProvinceModel> {
    List<ProvinceModel> findByIdIn(List<Long> ids);
}
