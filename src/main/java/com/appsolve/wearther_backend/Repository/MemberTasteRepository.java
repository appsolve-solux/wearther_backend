package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.MemberTaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberTasteRepository extends JpaRepository<MemberTaste, Long> {
    List<MemberTaste> findByMemberId(Long memberId); // 특정 사용자의 취향 조회

    void deleteByMemberId(Long memberId); // 특정 사용자의 모든 취향 삭제
}