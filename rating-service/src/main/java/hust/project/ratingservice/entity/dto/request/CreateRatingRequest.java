package hust.project.ratingservice.entity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRatingRequest {
    private Long productId;
    private String productName;
    private String content;
    private Integer ratingStar;
}
