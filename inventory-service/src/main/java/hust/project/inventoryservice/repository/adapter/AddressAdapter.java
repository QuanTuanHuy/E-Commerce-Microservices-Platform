package hust.project.inventoryservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.inventoryservice.entity.dto.request.ValidateAddressRequest;
import hust.project.inventoryservice.entity.dto.response.ValidateAddressResponse;
import hust.project.inventoryservice.port.IAddressPort;
import hust.project.inventoryservice.repository.httpclient.IAddressClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressAdapter implements IAddressPort {
    private final IAddressClient addressClient;
    private final ObjectMapper objectMapper;

    @Override
    public ValidateAddressResponse validateAddress(ValidateAddressRequest request) {
        var response = addressClient.validateAddress(request).getBody().getData();
        log.info("[AddressAdapter] validateAddress response: {}", response);

        return objectMapper.convertValue(response, ValidateAddressResponse.class);
    }
}
