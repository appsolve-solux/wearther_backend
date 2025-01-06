package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    @Query("SELECT m.constitution FROM MemberEntity m WHERE m.memberId = :memberId")
    int findConstitutionByMemberId(@Param("memberId") Long memberId);

    @Modifying
    @Transactional
    @Query("UPDATE MemberEntity m SET m.constitution = :constitution WHERE m.memberId = :memberId")
    void updateConstitutionByMemberId(@Param("memberId") Long memberId, @Param("constitution") int constitution);
}
