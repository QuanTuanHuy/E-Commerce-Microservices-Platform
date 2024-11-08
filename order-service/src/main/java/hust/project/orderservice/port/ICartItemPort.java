package hust.project.orderservice.port;

import hust.project.orderservice.entity.dto.request.DeleteCartItemRequest;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.List;

public interface ICartItemPort {
    @Retry(name = "restApi")
    void adjustOrDeleteCartItems(List<DeleteCartItemRequest> requests);
}
