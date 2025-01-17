package com.appsolve.wearther_backend.auth.Entity;

import com.appsolve.wearther_backend.Entity.MemberEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Setter
@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String deviceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    private String refreshToken;


    @Builder
    public RefreshToken(MemberEntity member, String refreshToken,String deviceId) {
        this.member = member;
        this.refreshToken = refreshToken;
        this.deviceId = deviceId;
    }

}
