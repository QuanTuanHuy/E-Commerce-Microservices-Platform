package hust.project.productservice.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "slug", unique = true)
    private String slug;

    @Column(name = "is_published")
    private Boolean isPublished;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "image_id")
    private Long imageId;
}
