package hust.project.searchservice.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_PRODUCT_FAILED(100002, "Create product failed", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(100003, "Product not found", HttpStatus.NOT_FOUND),
    DELETE_PRODUCT_FAILED(100004, "Delete product failed", HttpStatus.BAD_REQUEST),
    UPDATE_PRODUCT_FAILED(100005, "Update product failed", HttpStatus.BAD_REQUEST),
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
