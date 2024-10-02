package hust.project.identityservice.service.impl;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.dto.request.CreateRoleRequest;
import hust.project.identityservice.service.IRoleService;
import hust.project.identityservice.usercase.CreateRoleUseCase;
import hust.project.identityservice.usercase.DeleteRoleUseCase;
import hust.project.identityservice.usercase.GetRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService {
    private final CreateRoleUseCase createRoleUseCase;
    private final GetRoleUseCase getRoleUseCase;
    private final DeleteRoleUseCase deleteRoleUseCase;

    @Override
    public RoleEntity createRole(CreateRoleRequest request) {
        return createRoleUseCase.createRole(request);
    }

    @Override
    public RoleEntity getDetailRole(Long id) {
        return getRoleUseCase.getDetailRole(id);
    }

    @Override
    public List<RoleEntity> getAllRoles() {
        return getRoleUseCase.getAllRoles();
    }

    @Override
    public void deleteRole(Long id) {
        deleteRoleUseCase.deleteRole(id);
    }
}
