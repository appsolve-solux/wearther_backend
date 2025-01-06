package com.appsolve.wearther_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;  // Primary key: member_id

    private String memberEmail;
    private String loginId;
    private String userPw;
    private Integer constitution;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long closetId;

}