package hust.project.orderservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    private Long id;
    private String name;
    private String slug;
    private Double price;
    private Boolean isPublished;
}
