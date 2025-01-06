package com.appsolve.wearther_backend.dto;

import lombok.Data;

@Data
public class SignInRequest {
    private String loginId;
    private String password;
}
