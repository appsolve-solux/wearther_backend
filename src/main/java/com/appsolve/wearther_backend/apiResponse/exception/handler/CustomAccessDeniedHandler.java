package com.appsolve.wearther_backend.apiResponse.exception.handler;

import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
//권한이 없는 경우 동작함
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCode errorCode = ErrorCode._FORBIDDEN;

        CustomException customException = new CustomException(errorCode);

        ApiResponse<Object> apiResponse = ApiResponse.fail(customException, null);

        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

    }
}
