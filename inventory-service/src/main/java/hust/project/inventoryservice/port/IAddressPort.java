package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.dto.request.ValidateAddressRequest;
import hust.project.inventoryservice.entity.dto.response.ValidateAddressResponse;

public interface IAddressPort {
    ValidateAddressResponse validateAddress(ValidateAddressRequest request);
}
