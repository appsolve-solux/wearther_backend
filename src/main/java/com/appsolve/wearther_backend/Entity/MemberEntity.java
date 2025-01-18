package com.appsolve.wearther_backend.Entity;

import com.appsolve.wearther_backend.auth.Entity.RefreshToken;
import com.appsolve.wearther_backend.closet.entity.Closet;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor @Setter
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;  // Primary key: member_id
    @Email
    @Column(unique = true)
    private String memberEmail;
    private String loginId;
    private String userPw;
    private Integer constitution;



    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "closet_id")
    private Closet closet;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RefreshToken> refreshTokens = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTaste> memberTastes = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Location> locations = new ArrayList<>();


}
