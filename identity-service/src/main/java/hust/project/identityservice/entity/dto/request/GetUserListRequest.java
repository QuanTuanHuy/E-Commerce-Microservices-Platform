package hust.project.identityservice.entity.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetUserListRequest {
    private List<Long> userIds;
    private List<Long> roleIds;
    private String email;
    private String phoneNumber;
}
