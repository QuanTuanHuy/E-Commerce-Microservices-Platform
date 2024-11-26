package hust.project.promotionservice.repository.httpClient;

import hust.project.promotionservice.config.FeignClientConfig;
import hust.project.promotionservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "product-client",
        url = "${app.services.product-service.url}",
        configuration = FeignClientConfig.class
)
public interface IProductClient {
    @GetMapping("/api/v1/products/by_ids")
    ResponseEntity<Resource> getProductsByIds(@RequestParam(name = "ids") List<Long> ids);

    @GetMapping("/api/v1/products/by_brand_id")
    ResponseEntity<Resource> getProductsByBrandIds(@RequestParam(name = "brand_ids") List<Long> brandId);

    @GetMapping("/api/v1/products/by_category_id")
    ResponseEntity<Resource> getProductsByCategoryIds(@RequestParam(name = "category_ids") List<Long> categoryId);

    @GetMapping("/api/v1/brands/by_ids")
    ResponseEntity<Resource> getBrandsByIds(@RequestParam(name = "ids") List<Long> ids);

    @GetMapping("/api/v1/categories/by_ids")
    ResponseEntity<Resource> getCategoriesByIds(@RequestParam(name = "ids") List<Long> ids);
}
