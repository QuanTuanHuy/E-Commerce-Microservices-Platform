package hust.project.addressservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WardEntity {
    private Long id;

    private String code;

    private String name;

    private Long districtId;
}
