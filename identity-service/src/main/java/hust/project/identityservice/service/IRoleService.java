package hust.project.identityservice.service;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.dto.request.CreateRoleRequest;

import java.util.List;

public interface IRoleService {
    RoleEntity createRole(CreateRoleRequest request);

    RoleEntity getDetailRole(Long id);

    List<RoleEntity> getAllRoles();

    void deleteRole(Long id);
}
