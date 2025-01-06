package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.MemberTaste;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberTasteRepository extends JpaRepository<MemberTaste, Long> {
    List<MemberTaste> findByMemberMemberId(Long memberId);
}

