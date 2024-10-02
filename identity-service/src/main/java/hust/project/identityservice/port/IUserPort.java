package hust.project.identityservice.port;

import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.entity.dto.response.PageInfo;
import org.springframework.data.util.Pair;

import java.util.List;

public interface IUserPort {
    UserEntity save(UserEntity userEntity);

    UserEntity getByEmail(String email);

    UserEntity getById(Long id);

    Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter);
}
