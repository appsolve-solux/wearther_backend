package com.appsolve.wearther_backend.profileEdit;

import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/profile-edit")
public class ProfileEditController {

    @Autowired
    private ProfileEditService profileEditService;

    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<ProfileEditResponseDto>> getProfile(@PathVariable Long memberId){
        ProfileEditResponseDto dto = profileEditService.getProfileByMemberId(memberId);
        return ApiResponse.success(HttpStatus.OK, dto);
    }


}