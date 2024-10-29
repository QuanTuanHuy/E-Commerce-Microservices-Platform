package hust.project.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "is_published")
    private Boolean isPublished;

    @Column(name = "is_has_options")
    private Boolean isHasOptions;

    @Column(name = "stock_quantity")
    private Long stockQuantity;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Column(name = "parent_id")
    private Long parentId;

}
