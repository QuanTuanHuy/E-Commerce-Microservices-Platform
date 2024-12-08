package hust.project.ratingservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RatingEntity {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long productId;
    private String productName;
    private Integer ratingStar;
    private String content;
}
