package com.appsolve.wearther_backend.Dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpRequest {
    private String loginId;
    private String password;
    @Email
    private String email;
    private int constitution;

    //건너뛰기 가능한 영역
    private ClosetDto closetDto;
    private TasteDto tasteDto;
}
