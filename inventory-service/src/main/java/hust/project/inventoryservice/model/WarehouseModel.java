package hust.project.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "warehouses")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseModel extends AuditTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "address_id")
    private Long addressId;
}
