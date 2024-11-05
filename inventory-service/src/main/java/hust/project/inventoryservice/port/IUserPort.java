package hust.project.inventoryservice.port;

import hust.project.inventoryservice.entity.dto.request.GetUserListRequest;
import hust.project.inventoryservice.entity.dto.response.UserInfoResponse;

import java.util.List;

public interface IUserPort {
    List<UserInfoResponse> getAllUserInfos(GetUserListRequest request);
}
