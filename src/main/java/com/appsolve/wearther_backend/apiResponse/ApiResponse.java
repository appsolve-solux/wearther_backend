package com.appsolve.wearther_backend.apiResponse;

import com.appsolve.wearther_backend.apiResponse.dto.ExceptionDto;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record ApiResponse<T> (
                             HttpStatus httpStatus,
                             boolean success,
                             @Nullable T result,
                             @Nullable ExceptionDto error) {

    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatus httpStatus, @Nullable final T result) {
        ApiResponse<T> response = new ApiResponse<>(httpStatus, true, result, null);
        return new ResponseEntity<>(response, httpStatus);
    }

    public static <T> ApiResponse<T> fail(final CustomException e , @Nullable final T result) {
        return new ApiResponse<>(e.getHttpStatus(), false, result, ExceptionDto.of(e.getErrorCode()));
    }


}
