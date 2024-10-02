package hust.project.identityservice.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleEntity {
    private Long id;

    private String name;

    private String description;
}
