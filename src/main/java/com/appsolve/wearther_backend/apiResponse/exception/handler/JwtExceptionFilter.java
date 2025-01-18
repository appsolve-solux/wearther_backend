package com.appsolve.wearther_backend.apiResponse.exception.handler;

import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response); // 다음 필터로 요청 전달
        } catch (ExpiredJwtException e) {
            log.error("[JwtExceptionFilter] :: JWT 토큰이 만료되었습니다.");
            handleException(response, ErrorCode.TOKEN_EXPIRED, "JWT 토큰이 만료되었습니다.");
        } catch (JwtException e) {
            log.error("[JwtExceptionFilter] :: 유효하지 않은 JWT 토큰입니다. {}", e.getMessage());
            handleException(response, ErrorCode.INVALID_TOKEN, "유효하지 않은 JWT 토큰입니다.");
        } catch (Exception e) {
            log.error("[JwtExceptionFilter] :: 기타 예외 발생. {}", e.getMessage());
            throw e;
        }
    }

    private void handleException(HttpServletResponse response, ErrorCode errorCode, String message) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ApiResponse<Object> apiResponse = ApiResponse.fail(new CustomException(errorCode), null);
        objectMapper.writeValue(response.getWriter(), apiResponse);
    }
}
