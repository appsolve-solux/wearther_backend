package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import com.appsolve.wearther_backend.Repository.MemberTasteRepository;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired private MemberTasteRepository memberTasteRepository;

    public int getConstitutionByMemberId(Long memberId){
        int number = memberRepository.findConstitutionByMemberId(memberId);
        return number;
    }

    @Transactional
    public void updateConstitutionByMemberId(Long memberId, Integer constitution){
        memberRepository.updateConstitutionByMemberId(memberId, constitution);
    }

    public MemberEntity getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode._BAD_REQUEST));
    }

    public List<Long> getMemberTastes(Long memberId) {
        return memberTasteRepository.findByMemberMemberId(memberId)
                .stream()
                .map(memberTaste -> memberTaste.getTaste().getId())
                .collect(Collectors.toList());
    }

    public void deleteMember(Long memberId){
        if (memberRepository.existsById(memberId)){
            memberRepository.deleteById(memberId);
        } else {
            throw new CustomException(ErrorCode._BAD_REQUEST);
        }
    }

    public void updatePasswordById(Long memberId, String newPassword){
        memberRepository.updateUserPwByMemberId(memberId, newPassword);
    }
}
