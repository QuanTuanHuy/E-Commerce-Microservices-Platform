package hust.project.addressservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String phoneNumber;

    private String addressDetail;

    private Long provinceId;

    private Long districtId;

    private Long wardId;
}
