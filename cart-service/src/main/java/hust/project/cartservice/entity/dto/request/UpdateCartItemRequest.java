package hust.project.cartservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCartItemRequest {
    @Min(1)
    private Long productId;
    @Min(1)
    private Integer quantity;
}
