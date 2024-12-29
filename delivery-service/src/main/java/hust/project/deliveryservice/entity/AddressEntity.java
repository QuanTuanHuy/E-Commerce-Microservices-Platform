package hust.project.deliveryservice.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {
    private Long id;

    private String city;

    private String district;

    private String ward;

    private String street;

    private String detail;
}
