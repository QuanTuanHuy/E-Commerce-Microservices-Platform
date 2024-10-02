package hust.project.identityservice.mapper;

import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.CreateUserRequest;
import hust.project.identityservice.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IUserMapper {
    UserEntity toEntityFromModel(UserModel userModel);

    UserModel toModelFromEntity(UserEntity userEntity);

    List<UserEntity> toEntitiesFromModels(List<UserModel> userModels);

    @Mapping(target = "password", ignore = true)
    UserEntity toEntityFromRequest(CreateUserRequest request);
}
