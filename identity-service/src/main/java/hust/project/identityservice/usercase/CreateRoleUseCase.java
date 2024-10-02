package hust.project.identityservice.usercase;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.dto.request.CreateRoleRequest;
import hust.project.identityservice.mapper.IRoleMapper;
import hust.project.identityservice.port.IRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateRoleUseCase {
    private final IRoleMapper roleMapper;
    private final IRolePort rolePort;

    public RoleEntity createRole(CreateRoleRequest request) {
        RoleEntity role = roleMapper.toEntityFromRequest(request);
        return rolePort.save(role);
    }
}
