package com.example.web_nhom_5.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebException extends RuntimeException {
    private ErrorCode errorCode;

    public WebException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
