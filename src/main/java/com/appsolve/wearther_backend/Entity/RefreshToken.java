package com.appsolve.wearther_backend.Entity;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    private String refreshToken;


    @Builder
    public RefreshToken(MemberEntity member, String refreshToken) {
        this.member = member;
        this.refreshToken = refreshToken;
    }

}
