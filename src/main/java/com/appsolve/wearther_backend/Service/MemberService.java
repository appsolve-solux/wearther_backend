package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public int getConstitutionByMemberId(Long memberId){
        int number = memberRepository.findConstitutionByMemberId(memberId);
        return number;
    }

    @Transactional
    public void updateConstitutionByMemberId(Long memberId, Integer constitution){
        memberRepository.updateConstitutionByMemberId(memberId, constitution);
    }


}
