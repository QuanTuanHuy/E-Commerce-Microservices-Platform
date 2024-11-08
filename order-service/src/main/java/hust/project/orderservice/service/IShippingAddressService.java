package hust.project.orderservice.service;

import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.GetShippingAddressRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IShippingAddressService {
    Pair<PageInfo, List<ShippingAddressEntity>> getAll(GetShippingAddressRequest filter);
}
