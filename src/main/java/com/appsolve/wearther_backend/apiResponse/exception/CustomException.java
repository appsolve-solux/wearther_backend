package com.appsolve.wearther_backend.apiResponse.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

    public String getMessage() {
        return errorCode.getMessage();
    }
    public HttpStatus getHttpStatus() {
        return errorCode.getHttpStatus();
    }

}
