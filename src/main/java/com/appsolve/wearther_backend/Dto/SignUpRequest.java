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
    private LocationPostRequestDto locationPostRequestDto;

}
