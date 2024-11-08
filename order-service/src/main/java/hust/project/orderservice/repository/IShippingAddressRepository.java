package hust.project.orderservice.repository;

import hust.project.orderservice.model.ShippingAddressModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShippingAddressRepository extends IBaseRepository<ShippingAddressModel> {
    List<ShippingAddressModel> findByIdIn(List<Long> ids);
}
