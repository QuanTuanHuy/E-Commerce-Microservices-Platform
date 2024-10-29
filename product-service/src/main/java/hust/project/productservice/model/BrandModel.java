package hust.project.productservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brand", uniqueConstraints = {
        @UniqueConstraint(columnNames = "slug"),
        @UniqueConstraint(columnNames = "name")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "slug")
    private String slug;

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "is_published")
    private Boolean isPublished;
}
