package hust.project.identityservice.usercase;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.port.IRolePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRoleUseCase {
    private final IRolePort rolePort;

    public RoleEntity getDetailRole(Long id) {
        return rolePort.getById(id);
    }

    public List<RoleEntity> getAllRoles() {
        return rolePort.getAll();
    }
}
