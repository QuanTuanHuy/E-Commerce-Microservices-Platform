package hust.project.productservice.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    private Long id;

    private String name;

    private String slug;

    private String description;

    private Double price;

    private Boolean isPublished;

    private Boolean isHasOptions;

    private Long stockQuantity;

    private Long brandId;

    private String thumbnailImage;

    private Long parentId;

    private BrandEntity brand;

    private List<CategoryEntity> categories;

    private List<ImageEntity> images;

    private List<ProductEntity> productVariants;

    private List<ProductEntity> relatedProducts;
}
