package hust.project.identityservice.port;

import hust.project.identityservice.entity.RoleEntity;

import java.util.List;

public interface IRolePort {
    RoleEntity save(RoleEntity roleEntity);

    RoleEntity getById(Long id);

    List<RoleEntity> getAll();

    void deleteById(Long id);
}
