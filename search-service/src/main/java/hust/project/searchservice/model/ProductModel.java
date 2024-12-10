package hust.project.searchservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;
import java.util.List;

@Document(indexName = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductModel {
    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    private String slug;

    @Field(type = FieldType.Double)
    private Double price;

    private Boolean isPublished;

    @Field(type = FieldType.Text)
    private String brand;

    @Field(type = FieldType.Keyword)
    private List<String> categories;

    @Field(type = FieldType.Date)
    private Instant createdAt;
}
