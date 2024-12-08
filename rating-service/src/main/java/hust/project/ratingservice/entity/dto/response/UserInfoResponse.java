package hust.project.ratingservice.entity.dto.response;

import lombok.*;

@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private String role;
}