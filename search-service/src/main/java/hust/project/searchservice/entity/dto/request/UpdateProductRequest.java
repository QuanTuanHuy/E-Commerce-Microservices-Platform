package hust.project.searchservice.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequest {
    private String name;

    private String slug;

    private Double price;

    private Boolean isPublished;

    private String brand;

    private List<String> categories;
}
