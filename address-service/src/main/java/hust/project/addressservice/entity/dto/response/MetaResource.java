package hust.project.addressservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetaResource {
    private Integer code;
    private String message;
}