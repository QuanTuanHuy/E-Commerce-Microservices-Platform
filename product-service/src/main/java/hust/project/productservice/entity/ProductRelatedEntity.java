package hust.project.productservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRelatedEntity {
    private Long id;

    private Long productId;

    private Long relatedProductId;

    private ProductEntity product;

    private ProductEntity relatedProduct;
}
