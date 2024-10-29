package hust.project.identityservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    private Long id;

    private String email;

    @JsonIgnore
    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String gender;

    private LocalDate dateOfBirth;

    private Long roleId;

    private Long addressId;

    private RoleEntity role;

//    private AddressEntity address;
}
