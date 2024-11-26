package hust.project.promotionservice.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_PROMOTION_FAILED(100002, "Create promotion failed", HttpStatus.BAD_REQUEST),
    GET_PROMOTION_FAILED(100003, "Get promotion failed", HttpStatus.NOT_FOUND),
    UPDATE_PROMOTION_FAILED(100004, "Update promotion failed", HttpStatus.BAD_REQUEST),
    DELETE_PROMOTION_FAILED(100005, "Delete promotion failed", HttpStatus.BAD_REQUEST),
    CREATE_PROMOTION_APPLY_FAILED(100006, "Create promotion apply failed", HttpStatus.BAD_REQUEST),
    DELETE_PROMOTION_APPLY_FAILED(100007, "Delete promotion apply failed", HttpStatus.BAD_REQUEST),
    CREATE_PROMOTION_USAGE_FAILED(100008, "Create promotion usage failed", HttpStatus.BAD_REQUEST),
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
