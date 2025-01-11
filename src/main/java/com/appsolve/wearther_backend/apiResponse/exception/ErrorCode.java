package com.appsolve.wearther_backend.apiResponse.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {


    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
    _NOT_FOUND_END_POINT(HttpStatus.NOT_FOUND, "COMMON40400", "존재하지 않는 API입니다."),
    _NOT_FOUND_RESOURCE(HttpStatus.NOT_FOUND, "COMMON40401", "요청한 리소스를 찾을 수 없습니다."),
    _METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "COMMON405", "허용되지 않은 요청 방식입니다."),
    _TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "COMMON429", "너무 많은 요청입니다. 잠시 후 다시 시도해주세요."),

    /*에러 추가 가능*/
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "존재하지 않는 사용자 ID입니다."),
    USER_NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED, "USER4002", "사용자 인증이 필요합니다."),
    DUPLICATE_LOCATION(HttpStatus.BAD_REQUEST, "LOCATION4001", "중복된 위치 정보입니다."),
    INDEX_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4003", "해당 사용자 정보에 존재하지 않는 인덱스입니다."),
    Weather_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4004", "날씨 정보를 가져올 수 없습니다");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;



}
