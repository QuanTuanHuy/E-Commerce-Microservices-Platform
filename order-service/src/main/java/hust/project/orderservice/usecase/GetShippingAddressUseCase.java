package hust.project.orderservice.usecase;

import hust.project.orderservice.entity.ShippingAddressEntity;
import hust.project.orderservice.entity.dto.request.GetShippingAddressRequest;
import hust.project.orderservice.entity.dto.response.PageInfo;
import hust.project.orderservice.port.IShippingAddressPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetShippingAddressUseCase {
    private final IShippingAddressPort shippingAddressPort;

    public Pair<PageInfo, List<ShippingAddressEntity>> getAll(GetShippingAddressRequest filter) {
        return shippingAddressPort.getAllShippingAddresses(filter);
    }
}
