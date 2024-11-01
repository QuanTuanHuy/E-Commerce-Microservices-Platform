package hust.project.addressservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "provinces")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProvinceModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name", unique = true)
    private String name;
}
