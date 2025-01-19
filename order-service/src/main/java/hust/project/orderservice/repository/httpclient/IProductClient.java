package hust.project.orderservice.repository.httpclient;

import hust.project.orderservice.config.FeignClientConfig;
import hust.project.orderservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.orderservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "product-service",
        configuration = FeignClientConfig.class
)
public interface IProductClient {
    @PutMapping("/api/v1/products/update_quantity")
    ResponseEntity<Resource> updateProductQuantity(@RequestBody List<UpdateProductQuantityRequest> requests);
}
