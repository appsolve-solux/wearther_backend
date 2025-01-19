package com.appsolve.wearther_backend.profileEdit;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/profile-edit")
public class ProfileEditController {

    @Autowired
    private ProfileEditService profileEditService;

    private final AuthService authService;

    public ProfileEditController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<ProfileEditResponseDto>> getProfile(){
        Long memberId = authService.extractMemberIdFromContext();

        ProfileEditResponseDto dto = profileEditService.getProfileByMemberId(memberId);
        return ApiResponse.success(HttpStatus.OK, dto);
    }


}