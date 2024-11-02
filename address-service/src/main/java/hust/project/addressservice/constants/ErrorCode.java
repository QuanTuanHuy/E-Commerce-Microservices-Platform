package hust.project.addressservice.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_PROVINCE_FAILED(100002, "Create province failed", HttpStatus.BAD_REQUEST),
    GET_PROVINCE_FAILED(100003, "Get province failed", HttpStatus.NOT_FOUND),
    UPDATE_PROVINCE_FAILED(100004, "Update province failed", HttpStatus.BAD_REQUEST),
    DELETE_PROVINCE_FAILED(100005, "Delete province failed", HttpStatus.BAD_REQUEST),
    CREATE_DISTRICT_FAILED(100006, "Create district failed", HttpStatus.BAD_REQUEST),
    GET_DISTRICT_FAILED(100007, "Get district failed", HttpStatus.NOT_FOUND),
    UPDATE_DISTRICT_FAILED(100008, "Update district failed", HttpStatus.BAD_REQUEST),
    DELETE_DISTRICT_FAILED(100009, "Delete district failed", HttpStatus.BAD_REQUEST),
    CREATE_WARD_FAILED(100010, "Create ward failed", HttpStatus.BAD_REQUEST),
    GET_WARD_FAILED(100011, "Get ward failed", HttpStatus.NOT_FOUND),
    UPDATE_WARD_FAILED(100012, "Update ward failed", HttpStatus.BAD_REQUEST),
    DELETE_WARD_FAILED(100013, "Delete ward failed", HttpStatus.BAD_REQUEST),
    CREATE_ADDRESS_FAILED(100014, "Create address failed", HttpStatus.BAD_REQUEST),
    GET_ADDRESS_FAILED(100015, "Get address failed", HttpStatus.NOT_FOUND),
    UPDATE_ADDRESS_FAILED(100016, "Update address failed", HttpStatus.BAD_REQUEST),
    DELETE_ADDRESS_FAILED(100017, "Delete address failed", HttpStatus.BAD_REQUEST)

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
