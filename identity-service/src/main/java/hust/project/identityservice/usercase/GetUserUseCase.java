package hust.project.identityservice.usercase;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.entity.dto.response.PageInfo;
import hust.project.identityservice.port.IRolePort;
import hust.project.identityservice.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserUseCase {

    private final IUserPort userPort;
    private final IRolePort rolePort;

    public UserEntity getDetailUser(Long userId) {
        UserEntity user = userPort.getById(userId);
        RoleEntity role = rolePort.getById(user.getRoleId());

        user.setRole(role);
        return user;
    }


    public Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter) {
        var result = userPort.getAll(filter);

        List<RoleEntity> roles = rolePort.getAll();
        result.getSecond().forEach(user -> {
            RoleEntity role = roles.stream().filter(r -> r.getId().equals(user.getRoleId())).findFirst().orElse(null);
            user.setRole(role);
        });

        return result;
    }
}
