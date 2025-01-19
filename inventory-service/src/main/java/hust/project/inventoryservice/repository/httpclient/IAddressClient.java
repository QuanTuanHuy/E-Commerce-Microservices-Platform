package hust.project.inventoryservice.repository.httpclient;

import hust.project.inventoryservice.config.FeignClientConfig;
import hust.project.inventoryservice.entity.dto.request.ValidateAddressRequest;
import hust.project.inventoryservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "address-service",
        configuration = FeignClientConfig.class
)
public interface IAddressClient {
    @PostMapping("/api/v1/address/validate")
    ResponseEntity<Resource> validateAddress(@RequestBody ValidateAddressRequest request);
}
