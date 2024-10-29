package hust.project.productservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageEntity {
    private Long id;

    private String image;

    private String entityType;

    private Long entityId;
}
