package hust.project.productservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCategoryEntity {
    private Long id;

    private Long productId;

    private Long categoryId;

    private CategoryEntity category;
}
