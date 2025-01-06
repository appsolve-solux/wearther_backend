package com.appsolve.wearther_backend.Controller;


import com.appsolve.wearther_backend.Service.MemberTasteService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member-taste")
public class MemberTasteController {

    private final MemberTasteService memberTasteService;

    public MemberTasteController(MemberTasteService memberTasteService) {
        this.memberTasteService = memberTasteService;
    }

    @PatchMapping("/update/{memberId}")
    public ResponseEntity<ApiResponse<List<Long>>> updateTastes(@PathVariable Long memberId, @RequestBody List<Long> tasteIds) {
        memberTasteService.updateMemberTastes(memberId, tasteIds);
        return ApiResponse.success(HttpStatus.OK, tasteIds);
    }

    @GetMapping("/get/{memberId}")
    public ResponseEntity<ApiResponse<List<Long>>> getTastes(@PathVariable Long memberId) {
        List<Long> tastes = memberTasteService.getMemberTasteIds(memberId);
        return ApiResponse.success(HttpStatus.OK, tastes);
    }
}