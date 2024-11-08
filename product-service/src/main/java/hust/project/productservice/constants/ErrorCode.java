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
    CREATE_CATEGORY_FAILED(100009, "Create category failed", HttpStatus.BAD_REQUEST),
    GET_CATEGORY_FAILED(100010, "Get category failed", HttpStatus.NOT_FOUND),
    UPDATE_CATEGORY_FAILED(100011, "Update category failed", HttpStatus.BAD_REQUEST),
    DELETE_CATEGORY_FAILED(100012, "Delete category failed", HttpStatus.BAD_REQUEST),
    CREATE_PRODUCT_CATEGORY_FAILED(100013, "Create product category failed", HttpStatus.BAD_REQUEST),
    GET_PRODUCT_CATEGORY_FAILED(100014, "Get product category failed", HttpStatus.NOT_FOUND),
    DELETE_PRODUCT_CATEGORY_FAILED(100015, "Delete product category failed", HttpStatus.BAD_REQUEST),
    CREATE_PRODUCT_FAILED(100016, "Create product failed", HttpStatus.BAD_REQUEST),
    GET_PRODUCT_FAILED(100017, "Get product failed", HttpStatus.NOT_FOUND),
    UPDATE_PRODUCT_FAILED(100018, "Update product failed", HttpStatus.BAD_REQUEST),
    UPDATE_PRODUCT_QUANTITY_FAILED(100019, "Update product quantity failed", HttpStatus.BAD_REQUEST),
    DELETE_PRODUCT_FAILED(100019, "Delete product failed", HttpStatus.BAD_REQUEST),
    CREATE_PRODUCT_RELATED_FAILED(100020, "Create product related failed", HttpStatus.BAD_REQUEST),
    DELETE_PRODUCT_RELATED_FAILED(100021, "Delete product related failed", HttpStatus.BAD_REQUEST),
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
