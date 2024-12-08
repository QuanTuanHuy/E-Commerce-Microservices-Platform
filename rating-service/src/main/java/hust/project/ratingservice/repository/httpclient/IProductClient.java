package hust.project.ratingservice.repository.httpclient;

import hust.project.ratingservice.config.FeignClientConfig;
import hust.project.ratingservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "product-client",
        url = "${app.services.product-service.url}",
        configuration = FeignClientConfig.class
)
public interface IProductClient {
    @GetMapping("/api/v1/products/variants")
    ResponseEntity<Resource> getProductVariants(@RequestParam(name = "parent_id") Long parentId);
}
