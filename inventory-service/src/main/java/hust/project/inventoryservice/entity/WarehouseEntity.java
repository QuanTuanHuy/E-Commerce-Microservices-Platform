package hust.project.inventoryservice.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseEntity {
    private Long id;

    private String name;

    private String code;

    private Long addressId;

    private WarehouseAddressEntity address;
}
