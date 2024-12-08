package hust.project.ratingservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppException extends RuntimeException {
    private hust.project.ratingservice.constant.ErrorCode errorCode;

    public AppException(hust.project.ratingservice.constant.ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
