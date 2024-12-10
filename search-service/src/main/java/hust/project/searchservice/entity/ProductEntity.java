package hust.project.searchservice.entity;

import lombok.*;

import java.time.Instant;
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

    private Double price;

    private Boolean isPublished;

    private String brand;

    private List<String> categories;

    private Instant createdAt;

}
