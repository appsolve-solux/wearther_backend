package com.appsolve.wearther_backend.Controller;


import com.appsolve.wearther_backend.Service.MemberTasteService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member-taste")
public class MemberTasteController {

    private final MemberTasteService memberTasteService;
    private final AuthService authService;

    public MemberTasteController(MemberTasteService memberTasteService, AuthService authService) {
        this.memberTasteService = memberTasteService;
        this.authService = authService;
    }


    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<List<Long>>> updateTastes( @RequestBody List<Long> tasteIds) {
        Long memberId = authService.extractMemberIdFromContext();
        memberTasteService.updateMemberTastes(memberId, tasteIds);
        return ApiResponse.success(HttpStatus.OK, tasteIds);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Long>>> getTastes() {
        Long memberId = authService.extractMemberIdFromContext();
        List<Long> tastes = memberTasteService.getMemberTasteIds(memberId);
        return ApiResponse.success(HttpStatus.OK, tastes);
    }
}