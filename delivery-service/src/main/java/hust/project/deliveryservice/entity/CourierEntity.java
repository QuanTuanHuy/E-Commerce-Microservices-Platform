package hust.project.deliveryservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourierEntity {
    private Long id;

    private String name;

    private String phoneNumber;
}
