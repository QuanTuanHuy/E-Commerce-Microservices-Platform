package hust.project.cartservice.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_CART_ITEM_FAILED(100002, "Create cart item failed", HttpStatus.INTERNAL_SERVER_ERROR),
    GET_CART_ITEM_FAILED(100003, "Get cart item failed", HttpStatus.NOT_FOUND),
    UPDATE_CART_ITEM_FAILED(100004, "Update cart item failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DELETE_CART_ITEM_FAILED(100005, "Delete cart item failed", HttpStatus.INTERNAL_SERVER_ERROR),
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
