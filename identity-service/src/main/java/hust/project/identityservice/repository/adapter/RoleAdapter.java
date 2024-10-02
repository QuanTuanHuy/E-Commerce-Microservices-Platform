package hust.project.identityservice.repository.adapter;

import hust.project.identityservice.constants.ErrorCode;
import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.exception.AppException;
import hust.project.identityservice.mapper.IRoleMapper;
import hust.project.identityservice.model.RoleModel;
import hust.project.identityservice.port.IRolePort;
import hust.project.identityservice.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleAdapter implements IRolePort {
    private final IRoleRepository roleRepository;
    private final IRoleMapper roleMapper;

    @Override
    public RoleEntity save(RoleEntity roleEntity) {
        try {
            RoleModel roleModel = roleMapper.toModelFromEntity(roleEntity);
            return roleMapper.toEntityFromModel(roleRepository.save(roleModel));
        } catch (Exception e) {
            log.error("[RoleAdapter] save role failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_ROLE_FAILED);
        }
    }

    @Override
    public RoleEntity getById(Long id) {
        return roleMapper.toEntityFromModel(roleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND)));
    }

    @Override
    public List<RoleEntity> getAll() {
        return roleMapper.toEntitiesFromModels(roleRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        try {
            roleRepository.deleteById(id);
        } catch (Exception e) {
            log.error("[RoleAdapter] delete role failed: {}", e.getMessage());
            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
        }
    }
}
