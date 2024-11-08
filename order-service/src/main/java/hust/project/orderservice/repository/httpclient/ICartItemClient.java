package hust.project.orderservice.repository.httpclient;

import hust.project.orderservice.config.FeignClientConfig;
import hust.project.orderservice.entity.dto.request.DeleteCartItemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "cart-item-client",
        url = "${app.services.cart-service.url}",
        configuration = FeignClientConfig.class
)
public interface ICartItemClient {
    @PostMapping("/api/v1/cart_items/adjust_or_delete")
    void adjustOrDeleteCartItems(@RequestBody List<DeleteCartItemRequest> requests);
}
