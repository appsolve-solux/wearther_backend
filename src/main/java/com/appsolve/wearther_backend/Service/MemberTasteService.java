package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Entity.Member;
import com.appsolve.wearther_backend.Entity.MemberTaste;
import com.appsolve.wearther_backend.Repository.MemberTasteRepository;
import com.appsolve.wearther_backend.init_data.entity.Taste;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberTasteService {

    private final MemberTasteRepository memberTasteRepository;

    public MemberTasteService(MemberTasteRepository memberTasteRepository) {
        this.memberTasteRepository = memberTasteRepository;
    }

    public void updateMemberTastes(Long memberId, List<Long> newTasteIds) {
        // 1. 기존 데이터 삭제
        memberTasteRepository.deleteByMemberId(memberId);

        // 2. 새로운 데이터 추가
        for (Long tasteId : newTasteIds) {
            MemberTaste memberTaste = new MemberTaste();
            memberTaste.setMember(new Member(memberId)); // Member 객체 설정
            memberTaste.setTaste(new Taste(tasteId));   // Taste 객체 설정
            memberTasteRepository.save(memberTaste);
        }
    }

    public List<Taste> getMemberTastes(Long memberId) {
        return memberTasteRepository.findByMemberId(memberId)
                .stream()
                .map(MemberTaste::getTaste)
                .collect(Collectors.toList());
    }
}