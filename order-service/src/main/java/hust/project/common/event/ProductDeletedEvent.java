package hust.project.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDeletedEvent implements ProductDomainEvent{
    private Long id;
}
