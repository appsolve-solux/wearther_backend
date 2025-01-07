package com.appsolve.wearther_backend.Controller;


import com.appsolve.wearther_backend.Service.MemberService;
import com.appsolve.wearther_backend.apiResponse.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/constitution/{memberId}")
    public ResponseEntity<ApiResponse<Integer>> getConstitution(@PathVariable Long memberId) {
        int constitution = memberService.getConstitutionByMemberId(memberId);
        return ApiResponse.success(HttpStatus.OK, constitution);
    }

    @PatchMapping("/constitution/update/{memberId}/{constitution}")
    public ResponseEntity<ApiResponse<Integer>> updateMemberConstitution(@PathVariable Long memberId, @PathVariable Integer constitution) {
        memberService.updateConstitutionByMemberId(memberId, constitution);
        return ApiResponse.success(HttpStatus.OK, constitution);
    }

    @DeleteMapping("/delete/{memberId}")
    public ResponseEntity<ApiResponse<Long>> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ApiResponse.success(HttpStatus.OK, memberId);
    }



}
