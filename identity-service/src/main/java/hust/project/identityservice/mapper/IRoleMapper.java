package hust.project.identityservice.mapper;

import hust.project.identityservice.entity.RoleEntity;
import hust.project.identityservice.entity.dto.request.CreateRoleRequest;
import hust.project.identityservice.model.RoleModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IRoleMapper {
    RoleEntity toEntityFromModel(RoleModel roleModel);

    List<RoleEntity> toEntitiesFromModels(List<RoleModel> roleModels);

    RoleModel toModelFromEntity(RoleEntity roleEntity);

    RoleEntity toEntityFromRequest(CreateRoleRequest request);
}
