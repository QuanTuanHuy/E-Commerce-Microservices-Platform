package hust.project.orderservice.port;

import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.GetShippingAddressRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IShippingAddressPort {
    ShippingAddressEntity save(ShippingAddressEntity entity);

    ShippingAddressEntity getShippingAddressById(Long id);

    Pair<PageInfo, List<ShippingAddressEntity>> getAllShippingAddresses(GetShippingAddressRequest filter);

    List<ShippingAddressEntity> getShippingAddressesByIds(List<Long> ids);

//    void deleteShippingAddress(Long id);
}
