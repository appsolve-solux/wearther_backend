package com.appsolve.wearther_backend.auth.Repository;

import com.appsolve.wearther_backend.auth.Entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {

    boolean existsByMember_MemberIdAndRefreshToken(Long userIdFromToken, String refreshToken);
    Optional<RefreshToken> findByMember_MemberId(Long memberId);
    Optional<Object> findByMember_MemberIdAndRefreshTokenAndDeviceId(Long memberId, String refreshToken, String deviceId);
}
