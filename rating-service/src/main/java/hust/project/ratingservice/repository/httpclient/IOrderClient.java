package hust.project.ratingservice.repository.httpclient;

import hust.project.ratingservice.config.FeignClientConfig;
import hust.project.ratingservice.entity.dto.response.Resource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "order-service",
        configuration = FeignClientConfig.class
)
public interface IOrderClient {
    @GetMapping("/api/v1/orders/finished")
    ResponseEntity<Resource> getOrderExistedByUserId(@RequestParam(name = "product_ids") List<Long> productIds);
}
