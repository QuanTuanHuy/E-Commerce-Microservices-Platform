package hust.project.cartservice.entity.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NotBlank
@AllArgsConstructor
@Builder
public class DeleteCartItemRequest {
    @NotBlank
    List<Long> productIds;
}
