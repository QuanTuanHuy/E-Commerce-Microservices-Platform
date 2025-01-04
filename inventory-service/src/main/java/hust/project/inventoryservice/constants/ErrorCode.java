package hust.project.inventoryservice.constants;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_WAREHOUSE_FAILED(100002, "Create warehouse failed", HttpStatus.BAD_REQUEST),
    GET_WAREHOUSE_FAILED(100003, "Get warehouse failed", HttpStatus.NOT_FOUND),
    UPDATE_WAREHOUSE_FAILED(100004, "Update warehouse failed", HttpStatus.BAD_REQUEST),
    DELETE_WAREHOUSE_FAILED(100005, "Delete warehouse failed", HttpStatus.BAD_REQUEST),
    CREATE_WAREHOUSE_ADDRESS_FAILED(100006, "Create warehouse address failed", HttpStatus.BAD_REQUEST),
    GET_WAREHOUSE_ADDRESS_FAILED(100007, "Get warehouse address failed", HttpStatus.NOT_FOUND),
    UPDATE_WAREHOUSE_ADDRESS_FAILED(100008, "Update warehouse address failed", HttpStatus.BAD_REQUEST),
    DELETE_WAREHOUSE_ADDRESS_FAILED(100009, "Delete warehouse address failed", HttpStatus.BAD_REQUEST),
    CREATE_STOCK_FAILED(100010, "Create stock failed", HttpStatus.BAD_REQUEST),
    GET_STOCK_FAILED(100011, "Get stock failed", HttpStatus.NOT_FOUND),
    UPDATE_STOCK_FAILED(100012, "Update stock failed", HttpStatus.BAD_REQUEST),
    DELETE_STOCK_FAILED(100013, "Delete stock failed", HttpStatus.BAD_REQUEST),
    CREATE_STOCK_HISTORY_FAILED(100014, "Create stock history failed", HttpStatus.BAD_REQUEST),
    STOCK_NOT_ENOUGH(100015, "Stock not enough", HttpStatus.BAD_REQUEST),
    CREATE_TICKET_FAILED(100016, "Create ticket failed", HttpStatus.BAD_REQUEST),
    GET_TICKET_FAILED(100017, "Get ticket failed", HttpStatus.NOT_FOUND),
    CREATE_TICKET_ITEM_FAILED(100018, "Create ticket item failed", HttpStatus.BAD_REQUEST),
    GET_TICKET_ITEM_FAILED(100019, "Get ticket item failed", HttpStatus.NOT_FOUND),
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
