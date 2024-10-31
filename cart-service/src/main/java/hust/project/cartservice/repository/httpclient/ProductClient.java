package hust.project.cartservice.repository.httpclient;

import hust.project.cartservice.config.FeignClientConfig;
import hust.project.cartservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${app.services.product-service.url}",
        configuration = {FeignClientConfig.class}
)
public interface ProductClient {
    @GetMapping("/api/v1/products/thumbnails")
    ResponseEntity<Resource> getAllProductThumbnails(@RequestParam(name = "product_ids") List<Long> productIds);
}
