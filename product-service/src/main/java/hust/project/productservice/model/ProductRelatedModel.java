package hust.project.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_related")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRelatedModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "related_product_id")
    private Long relatedProductId;
}
