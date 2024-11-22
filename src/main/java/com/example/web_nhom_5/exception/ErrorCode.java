package com.example.web_nhom_5.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    ROOM_NOT_FOUND(1100, "Room not found" ,HttpStatus.NOT_FOUND),
    LOCATION_NOT_FOUND(1101, "Location not found",HttpStatus.NOT_FOUND),
    SERVICE_EXISTED(1102, "Service already existed",HttpStatus.CONFLICT),
    SERVICE_NOT_FOUND(1103, "Service not found",HttpStatus.NOT_FOUND),
    BOOKING_NOT_FOUND(1104, "Booking not found",HttpStatus.NOT_FOUND),
    INVALID_BOOKING_CHECKIN_CHECKOUT(1105, "Invalid booking checkin checkout",HttpStatus.BAD_REQUEST),
    BOOKING_IS_PAID(1106, "Booking is payed",HttpStatus.BAD_REQUEST),
    INVALID_NUM(1107, "Invalid number",HttpStatus.BAD_REQUEST),
    BOOKING_HAS_BEEN_CANCELED(1108, "Booking has been canceled",HttpStatus.BAD_REQUEST),
    CANNOT_CANCEL_PAID_BOOKING(1109, "Cannot cancel booking",HttpStatus.BAD_REQUEST),
    BOOKING_IS_NOT_PAID(1109, "Booking is not paid",HttpStatus.BAD_REQUEST),
    DO_NOT_CHANGE_THIS(1110, "Do not change this booking",HttpStatus.BAD_REQUEST),
    ROOM_FULL(1111, "Room full",HttpStatus.BAD_REQUEST),
    UNCAUGHT_EXCEPTION(9999, "Uncaught exception", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1004, "Invalid key" , HttpStatus.BAD_REQUEST),
    USER_EXISTED(1001,"User already existed",HttpStatus.BAD_REQUEST),
    USERNAME_EXISTED(1002,"username chua it nhat 5 ki tu", HttpStatus.BAD_REQUEST),
    PASSWORD_EXISTED(1003,"mat khau phai chua it nhat 8 ki tu",HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1006,"user not already existed",HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1007,"user not authenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1008,"you do not have permission",HttpStatus.FORBIDDEN),
    PASSWORD_NOT_EXISTED(1005,"Wrong password", HttpStatus.BAD_REQUEST);

    private int code;
    private String message;
    private HttpStatusCode statusCode;
    ErrorCode(int code, String message , HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
