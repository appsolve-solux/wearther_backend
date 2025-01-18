package com.appsolve.wearther_backend.closet.entity;


import com.appsolve.wearther_backend.Entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
@Table(name="closet")
public class Closet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="closet_id")
    private Long id;

    @OneToOne @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Builder.Default
    @OneToMany(mappedBy = "closet", fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosetUpper> closetUppers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "closet", fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosetLower> closetLowers = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "closet", fetch = EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClosetOther> closetOthers = new ArrayList<>();

    public static Closet createClosetByMember(MemberEntity member) {
        Closet closet = Closet.builder().member(member).build();
        member.setCloset(closet);
        return closet;
    }

}