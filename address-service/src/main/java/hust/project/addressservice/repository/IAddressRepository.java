package hust.project.addressservice.repository;

import hust.project.addressservice.model.AddressModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddressRepository extends IBaseRepository<AddressModel> {
    List<AddressModel> findByIdIn(List<Long> ids);
}
