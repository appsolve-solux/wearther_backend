package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import com.appsolve.wearther_backend.Entity.MemberTaste;
import com.appsolve.wearther_backend.Repository.MemberRepository;
import com.appsolve.wearther_backend.Repository.MemberTasteRepository;
import com.appsolve.wearther_backend.auth.Service.AuthService;
import com.appsolve.wearther_backend.init_data.entity.Taste;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberTasteService {

    private final MemberTasteRepository memberTasteRepository;
    private final MemberRepository memberRepository;
    private final AuthService authService;

    public MemberTasteService(MemberTasteRepository memberTasteRepository,AuthService authService, MemberRepository memberRepository) {
        this.memberTasteRepository = memberTasteRepository;
        this.memberRepository = memberRepository;
        this.authService = authService;
    }

    @Transactional
    public void updateMemberTastes(Long memberId, List<Long> newTasteIds) {
        // 1. 기존 데이터 삭제
        memberTasteRepository.deleteByMemberId(memberId);

        // 2. 새로운 데이터 추가
        for (Long tasteId : newTasteIds) {
            MemberEntity member = MemberEntity.builder()
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


    public void createMemberTastes(MemberEntity member, List<Long> tasteIds) {

        for (Long tasteId : tasteIds) {

            Taste taste = Taste.builder()
                    .id(tasteId)
                    .build();

            MemberTaste memberTaste = MemberTaste.builder()
                    .member(member)
                    .taste(taste)
                    .build();

            memberTasteRepository.save(memberTaste);


        }
    }
}
