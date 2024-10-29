package hust.project.productservice.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    SAVE_IMAGE_FAILED(100002, "Save image failed", HttpStatus.BAD_REQUEST),
    GET_IMAGE_FAILED(100003, "Get image failed", HttpStatus.NOT_FOUND),
    DELETE_IMAGE_FAILED(100004, "Delete image failed", HttpStatus.BAD_REQUEST),
    CREATE_BRAND_FAILED(100005, "Create brand failed", HttpStatus.BAD_REQUEST),
    UPDATE_BRAND_FAILED(100006, "Update brand failed", HttpStatus.BAD_REQUEST),
    GET_BRAND_FAILED(100007, "Get brand failed", HttpStatus.NOT_FOUND),
    DELETE_BRAND_FAILED(100008, "Delete brand failed", HttpStatus.BAD_REQUEST),
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
