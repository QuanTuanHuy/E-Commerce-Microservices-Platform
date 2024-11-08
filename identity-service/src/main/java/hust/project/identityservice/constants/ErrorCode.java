package hust.project.identityservice.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(100002, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    CREATE_TOKEN_FAILED(100002, "Create token failed", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(100003, "You do not have permission", HttpStatus.FORBIDDEN),
    USER_NOT_FOUND(100004, "User not found", HttpStatus.NOT_FOUND),
    USER_EMAIL_EXISTED(100005, "Email existed", HttpStatus.BAD_REQUEST),
    CREATE_USER_FAILED(100006, "Create user failed", HttpStatus.BAD_REQUEST),
    CREATE_ROLE_FAILED(100007, "Create role failed", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND(100008, "Get role failed", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode httpStatusCode;
}
