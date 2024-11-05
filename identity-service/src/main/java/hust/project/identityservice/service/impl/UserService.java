package hust.project.identityservice.service.impl;

import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.CreateUserRequest;
import hust.project.identityservice.entity.dto.request.GetUserListRequest;
import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.entity.dto.response.PageInfo;
import hust.project.identityservice.entity.dto.response.UserInfoResponse;
import hust.project.identityservice.service.IUserService;
import hust.project.identityservice.usercase.CreateUserUseCase;
import hust.project.identityservice.usercase.GetUserUseCase;
import hust.project.identityservice.usercase.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @Override
    public UserEntity createUser(CreateUserRequest request) {
        return createUserUseCase.createUser(request);
    }

    @Override
    public UserEntity getDetailUser(Long userId) {
        return getUserUseCase.getDetailUser(userId);
    }

    @Override
    public List<UserInfoResponse> getAllUserInfos(GetUserListRequest request) {
        return getUserUseCase.getAllUserInfos(request);
    }

    @Override
    public Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter) {
        return getUserUseCase.getAll(filter);
    }

    @Override
    public UserEntity updateUserRole(Long userId, Long roleId) {
        return updateUserUseCase.updateUserRole(userId, roleId);
    }
}
