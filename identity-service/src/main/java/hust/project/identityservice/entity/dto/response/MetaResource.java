package hust.project.identityservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaResource {
    private Long code;
    private String message;
}
