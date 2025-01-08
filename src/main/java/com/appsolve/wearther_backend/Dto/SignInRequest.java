package com.appsolve.wearther_backend.Dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String loginId;
    private String password;
}
