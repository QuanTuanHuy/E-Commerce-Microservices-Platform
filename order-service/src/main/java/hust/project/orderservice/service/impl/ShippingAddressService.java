package hust.project.orderservice.service.impl;

import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.GetShippingAddressRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.service.IShippingAddressService;
import hust.project.orderservice.usecase.GetShippingAddressUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingAddressService implements IShippingAddressService {
    private final GetShippingAddressUseCase getShippingAddressUseCase;

    @Override
    public Pair<PageInfo, List<ShippingAddressEntity>> getAll(GetShippingAddressRequest filter) {
        return getShippingAddressUseCase.getAll(filter);
    }
}
