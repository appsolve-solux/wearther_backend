package com.appsolve.wearther_backend.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    private String refreshToken;
    private Date validity;

    @Builder
    public RefreshToken(MemberEntity member, String refreshToken, Date validity) {
        this.member = member;
        this.refreshToken = refreshToken;
        this.validity = validity;
    }

}
