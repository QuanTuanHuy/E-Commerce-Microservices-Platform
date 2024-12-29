package hust.project.common.event;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreatedEvent implements ProductDomainEvent {
    private Long id;
    private String name;
    private String slug;
    private Double price;
    private Boolean isPublished;
    private String brand;
    private List<String> categories;
}
