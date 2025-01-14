package com.appsolve.wearther_backend.apiResponse.exception.handler;

import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;

//로그인 하지 않은 사용자에게서 작동
@Slf4j
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        log.info("[CustomAuthenticationEntryPointHandler] :: {}", authException.getMessage());
        log.info("[CustomAuthenticationEntryPointHandler] :: {}", request.getRequestURL());

        ErrorCode errorCode = ErrorCode.INVALID_TOKEN;
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || authorizationHeader.trim().isEmpty()) {
            errorCode = ErrorCode.MISSING_TOKEN;
        }

        CustomException customException = new CustomException(errorCode);
        ApiResponse<Object> apiResponse = ApiResponse.fail(customException, null);

        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        objectMapper.writeValue(response.getWriter(), apiResponse);

    }
}

