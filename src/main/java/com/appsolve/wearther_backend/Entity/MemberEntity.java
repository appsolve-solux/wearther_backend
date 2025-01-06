package com.appsolve.wearther_backend.Entity;

import com.appsolve.wearther_backend.closet.entity.Closet;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Data
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor @Setter
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;  // Primary key: member_id
    private String memberEmail;
    private String loginId;
    private String userPw;
    private Integer constitution;

    @Builder.Default
    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "closet_id")
    private Closet closet;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTaste> memberTastes = new ArrayList<>(); 

}