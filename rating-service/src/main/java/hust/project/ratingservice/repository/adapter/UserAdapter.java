package hust.project.ratingservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.ratingservice.entity.dto.response.UserInfoResponse;
import hust.project.ratingservice.port.IUserPort;
import hust.project.ratingservice.repository.httpclient.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdapter implements IUserPort {
    private final IUserClient userClient;
    private final ObjectMapper objectMapper;

    @Override
    public UserInfoResponse getUserInfoById(Long userId) {
        var response = userClient.getAllUserInfos(List.of(userId)).getBody().getData();

        if (response == null) {
            return null;
        }

        List<UserInfoResponse> users = objectMapper.convertValue(response, objectMapper.getTypeFactory()
                .constructCollectionType(List.class, UserInfoResponse.class));

        return users.get(0);
    }
}
