package com.appsolve.wearther_backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED )
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    @NotNull
    @Column(name = "login_id", unique = true)
    private String loginId;
    @NotNull
    private String member_pw;
    @Column(name = "member_name", unique = true)
    private String memberName;

    private int constitution;
//
//    @ManyToMany
//    @JoinTable(
//            name = "member_taste",
//            joinColumns = @JoinColumn(name = "member_id"),
//            inverseJoinColumns = @JoinColumn(name = "taste_id")
//    )
//    private List<Taste> taste;
//
//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(name = "closet_id")
//    private Closet closet;

    @Email
    @Column(unique = true)
    private String member_email;

    @Builder
    public Member(String login_id,String member_email,String member_pw, String member_name, int constitution) {
        this.constitution = constitution;
        this.loginId = login_id;
        this.member_email = member_email;
        this.member_pw = member_pw;
        this.memberName = member_name;
    }

}
