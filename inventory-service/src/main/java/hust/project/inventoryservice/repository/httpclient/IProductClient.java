package hust.project.inventoryservice.repository.httpclient;

import hust.project.inventoryservice.config.FeignClientConfig;
import hust.project.inventoryservice.entity.dto.request.UpdateProductQuantityRequest;
import hust.project.inventoryservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "product-service",
        configuration = FeignClientConfig.class
)
public interface IProductClient {
    @GetMapping("/api/v1/products/thumbnails")
    ResponseEntity<Resource> getProductList(
            @RequestParam(name = "product_ids") List<Long> productIds,
            @RequestParam(name = "slug") String slug,
            @RequestParam(name = "name") String name
    );

    @PutMapping("/api/v1/products/update_quantity")
    ResponseEntity<Resource> updateProductQuantity(@RequestBody List<UpdateProductQuantityRequest> requests);
}
