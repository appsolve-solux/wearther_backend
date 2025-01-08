package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.MemberTaste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberTasteRepository extends JpaRepository<MemberTaste, Long> {
    List<MemberTaste> findByMemberMemberId(Long memberId);

    @Modifying
    @Query("DELETE FROM MemberTaste mt WHERE mt.member.id = :memberId")
    void deleteByMemberId(@Param("memberId") Long memberId);
}

