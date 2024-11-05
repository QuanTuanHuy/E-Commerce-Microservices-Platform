package hust.project.inventoryservice.repository.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import hust.project.inventoryservice.entity.dto.request.GetUserListRequest;
import hust.project.inventoryservice.entity.dto.response.UserInfoResponse;
import hust.project.inventoryservice.port.IUserPort;
import hust.project.inventoryservice.repository.httpclient.IIdentityClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAdapter implements IUserPort {
    private final IIdentityClient identityClient;
    private final ObjectMapper objectMapper;

    @Override
    public List<UserInfoResponse> getAllUserInfos(GetUserListRequest request) {
        var response = identityClient.getAllUserInfos(request.getUserIds()).getBody().getData();

        if (response == null) {
            return List.of();
        }

        return objectMapper.convertValue(response,
                objectMapper.getTypeFactory().constructCollectionType(List.class, UserInfoResponse.class));
    }
}
