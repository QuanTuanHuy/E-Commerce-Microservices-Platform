package hust.project.ratingservice.port;

import hust.project.ratingservice.entity.dto.response.UserInfoResponse;

public interface IUserPort {
    UserInfoResponse getUserInfoById(Long userId);
}
