package hust.project.identityservice.entity.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserRequest {
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String gender;

    private LocalDate dateOfBirth;
}
