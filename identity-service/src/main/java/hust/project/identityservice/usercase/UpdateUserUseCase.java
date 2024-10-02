package hust.project.identityservice.usercase;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.port.IRolePort;
import hust.project.identityservice.port.IUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {
    private final IUserPort userPort;
    private final IRolePort rolePort;

    public UserEntity updateUserRole(Long userId, Long roleId) {
        UserEntity user = userPort.getById(userId);
        RoleEntity role = rolePort.getById(roleId);
        user.setRoleId(roleId);

        return userPort.save(user);
    }
}
