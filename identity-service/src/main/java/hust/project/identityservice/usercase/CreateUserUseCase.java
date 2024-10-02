package hust.project.identityservice.usercase;

import hust.project.identityservice.constants.ErrorCode;
import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.CreateUserRequest;
import hust.project.identityservice.exception.AppException;
import hust.project.identityservice.mapper.IUserMapper;
import hust.project.identityservice.port.IRolePort;
import hust.project.identityservice.port.IUserPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserUseCase {
    private final IUserPort userPort;
    private final IRolePort rolePort;
    private final IUserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    private static final Long USER_ROLE_ID = 2L;
    private static final Long MODERATOR_ROLE_ID = 3L;

    public UserEntity createUser(CreateUserRequest request) {
        UserEntity existedUser = userPort.getByEmail(request.getEmail());
        if (existedUser != null) {
            log.error("[CreateUserUseCase] email existed: {}", request.getEmail());
            throw new AppException(ErrorCode.USER_EMAIL_EXISTED);
        }

        UserEntity user = userMapper.toEntityFromRequest(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRoleId(USER_ROLE_ID);

        user = userPort.save(user);
        RoleEntity role = rolePort.getById(user.getRoleId());
        user.setRole(role);

        return user;
    }
}
