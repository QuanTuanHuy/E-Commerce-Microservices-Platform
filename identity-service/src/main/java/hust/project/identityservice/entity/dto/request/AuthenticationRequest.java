package hust.project.identityservice.entity.dto.request;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    String email;
    String password;
}
