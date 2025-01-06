package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Entity.Member;
import com.appsolve.wearther_backend.Entity.MemberTaste;
import com.appsolve.wearther_backend.Repository.MemberTasteRepository;
import com.appsolve.wearther_backend.init_data.entity.Taste;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberTasteService {

    private final MemberTasteRepository memberTasteRepository;

    public MemberTasteService(MemberTasteRepository memberTasteRepository) {
        this.memberTasteRepository = memberTasteRepository;
    }

    @Transactional
    public void updateMemberTastes(Long memberId, List<Long> newTasteIds) {
        // 1. 기존 데이터 삭제
        memberTasteRepository.deleteByMemberId(memberId);

        // 2. 새로운 데이터 추가
        for (Long tasteId : newTasteIds) {
            Member member = Member.builder()
                    .memberId(memberId)
                    .build();

            Taste taste = Taste.builder()
                    .id(tasteId)
                    .build();

            MemberTaste memberTaste = MemberTaste.builder()
                    .member(member)
                    .taste(taste)
                    .build();

            memberTasteRepository.save(memberTaste); // 데이터베이스에 저장
        }
    }


    public List<Long> getMemberTasteIds(Long memberId) {
        return memberTasteRepository.findByMemberMemberId(memberId)
                .stream()
                .map(memberTaste -> memberTaste.getTaste().getId())  // Taste 엔티티에서 id 값 추출
                .collect(Collectors.toList());
    }


}
