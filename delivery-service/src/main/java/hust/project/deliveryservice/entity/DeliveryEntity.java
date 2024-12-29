package hust.project.deliveryservice.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryEntity {
    private Long id;

    private Long orderId;

    private String state;

    private Long assignedCourierId;

    private Long pickUpAddressId;

    private Long deliveryAddressId;

    private LocalDateTime pickUpTime;

    private LocalDateTime deliveryTime;


    private CourierEntity assignedCourier;

    private AddressEntity pickUpAddress;

    private AddressEntity deliveryAddress;

    private List<ActionEntity> actions;
}
