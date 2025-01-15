package com.appsolve.wearther_backend.Repository;

import com.appsolve.wearther_backend.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    boolean existsByMember_MemberIdAndRefreshToken(Long userIdFromToken, String refreshToken);

    boolean existsByMember_MemberId(Long memberId);

    void deleteByMember_MemberId(Long memberId);

    RefreshToken findByMember_MemberId(Long memberId);
}
