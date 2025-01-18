package com.appsolve.wearther_backend.Controller;

import com.appsolve.wearther_backend.Entity.MemberEntity;
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

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<List<Long>>> createTastes(@RequestHeader("Authorization") String token, @RequestBody List<Long> tasteIds) {
        memberTasteService.createMemberTastes(token, tasteIds);
        return ApiResponse.success(HttpStatus.OK, tasteIds);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<List<Long>>> updateTastes(@RequestHeader("Authorization") String token, @RequestBody List<Long> tasteIds) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        memberTasteService.updateMemberTastes(memberId, tasteIds);
        return ApiResponse.success(HttpStatus.OK, tasteIds);
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Long>>> getTastes(@RequestHeader("Authorization") String token) {
        MemberEntity member = authService.getMemberEntityFromToken(token);
        Long memberId = member.getMemberId();

        List<Long> tastes = memberTasteService.getMemberTasteIds(memberId);
        return ApiResponse.success(HttpStatus.OK, tastes);
    }
}