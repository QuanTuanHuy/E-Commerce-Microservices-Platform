package hust.project.addressservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "districts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistrictModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "province_id")
    private Long provinceId;
}
