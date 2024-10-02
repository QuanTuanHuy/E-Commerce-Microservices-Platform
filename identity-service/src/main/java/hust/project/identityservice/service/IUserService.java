package hust.project.identityservice.service;

import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.CreateUserRequest;
import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IUserService {
    UserEntity createUser(CreateUserRequest request);

    UserEntity getDetailUser(Long userId);

    Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter);

    UserEntity updateUserRole(Long userId, Long roleId);
}
