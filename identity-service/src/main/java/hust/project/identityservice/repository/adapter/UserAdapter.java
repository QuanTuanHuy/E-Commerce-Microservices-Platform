package hust.project.identityservice.repository.adapter;

import hust.project.identityservice.constants.ErrorCode;
import hust.project.identityservice.entity.UserEntity;
import hust.project.identityservice.entity.dto.request.GetUserListRequest;
import hust.project.identityservice.entity.dto.request.GetUserRequest;
import hust.project.identityservice.entity.dto.response.PageInfo;
import hust.project.identityservice.exception.AppException;
import hust.project.identityservice.mapper.IUserMapper;
import hust.project.identityservice.model.UserModel;
import hust.project.identityservice.port.IUserPort;
import hust.project.identityservice.repository.IUserRepository;
import hust.project.identityservice.repository.specification.UserSpecification;
import hust.project.identityservice.utils.PageInfoUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAdapter implements IUserPort {
    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    @Override
    public UserEntity save(UserEntity userEntity) {
        try {
            UserModel userModel = userMapper.toModelFromEntity(userEntity);
            return userMapper.toEntityFromModel(userRepository.save(userModel));
        } catch (Exception e) {
            log.error("[UserAdapter] save user failed: {}", e.getMessage());
            throw new AppException(ErrorCode.CREATE_USER_FAILED);
        }
    }

    @Override
    public UserEntity getByEmail(String email) {
        return userMapper.toEntityFromModel(userRepository.findByEmail(email).orElse(null));
    }

    @Override
    public UserEntity getById(Long id) {
        return userMapper.toEntityFromModel(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    @Override
    public List<UserEntity> getAllUsers(GetUserListRequest request) {
        return userMapper.toEntitiesFromModels(userRepository.findAll(UserSpecification.getAllUsers(request)));
    }

    @Override
    public Pair<PageInfo, List<UserEntity>> getAll(GetUserRequest filter) {
        Pageable pageable = PageRequest.of(Math.toIntExact(filter.getPage()),
                Math.toIntExact(filter.getPageSize()), Sort.by("id").descending());

        var userPage = userRepository.findAll(UserSpecification.getAll(filter), pageable);

        var pageInfo = PageInfoUtils.getPageInfo(userPage);

        return Pair.of(pageInfo, userMapper.toEntitiesFromModels(userPage.getContent()));
    }
}
