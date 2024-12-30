package com.appsolve.wearther_backend.closet.entity;


import com.appsolve.wearther_backend.member.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="closet")
public class Closet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="closet_id")
    private Long id;

    @OneToOne @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "closet", fetch = FetchType.LAZY)
    private List<ClosetUpper> closetUppers = new ArrayList<>();

    @OneToMany(mappedBy = "closet", fetch = FetchType.LAZY)
    private List<ClosetLower> closetLowers = new ArrayList<>();

    @OneToMany(mappedBy = "closet", fetch = FetchType.LAZY)
    private List<ClosetOther> closetOthers = new ArrayList<>();
}