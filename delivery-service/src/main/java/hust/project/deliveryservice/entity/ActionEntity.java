package hust.project.deliveryservice.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionEntity {
    private Long id;

    private Long courierId;

    private Long deliveryId;

    private String actionType;

    private LocalDateTime time;

    private Long addressId;

    private AddressEntity address;
}
