package hust.project.addressservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictEntity {
    private Long id;

    private String code;

    private String name;

    private Long provinceId;
}
