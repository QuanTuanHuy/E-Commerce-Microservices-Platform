package hust.project.cartservice.entity.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartItemRequest {
    @NotNull
    private Long productId;
    @Min(1)
    private Integer quantity;
}
