package hust.project.orderservice.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(100001, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_ORDER_ITEM_FAILED(100002, "Create order item failed", HttpStatus.INTERNAL_SERVER_ERROR),
    UPDATE_ORDER_ITEM_FAILED(100003, "Update order item failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DELETE_ORDER_ITEM_FAILED(100004, "Delete order item failed", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_ORDER_FAILED(100005, "Create order failed", HttpStatus.INTERNAL_SERVER_ERROR),
    GET_ORDER_FAILED(100006, "Get order failed", HttpStatus.NOT_FOUND),
    UPDATE_ORDER_FAILED(100007, "Update order failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DELETE_ORDER_FAILED(100008, "Delete order failed", HttpStatus.INTERNAL_SERVER_ERROR),
    CANCEL_ORDER_FAILED(100009, "Cancel order failed", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_SHIPPING_ADDRESS_FAILED(100009, "Create shipping address failed", HttpStatus.INTERNAL_SERVER_ERROR),
    GET_SHIPPING_ADDRESS_FAILED(100010, "Get shipping address failed", HttpStatus.NOT_FOUND),
    UPDATE_SHIPPING_ADDRESS_FAILED(100011, "Update shipping address failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DELETE_SHIPPING_ADDRESS_FAILED(100012, "Delete shipping address failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DELETE_CART_ITEM_FAILED(100013, "Delete cart item failed", HttpStatus.INTERNAL_SERVER_ERROR),
    CREATE_PRODUCT_FAILED(100014, "Create product failed", HttpStatus.INTERNAL_SERVER_ERROR),
    GET_PRODUCT_FAILED(100015, "Get product failed", HttpStatus.NOT_FOUND),
    DELETE_PRODUCT_FAILED(100016, "Delete product failed", HttpStatus.INTERNAL_SERVER_ERROR),
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
