package com.appsolve.wearther_backend.closet.entity;

import com.appsolve.wearther_backend.init_data.entity.UpperWear;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="closet_upper")
public class ClosetUpper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closet_upper_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "closet_id")
    private Closet closet;

    @ManyToOne
    @JoinColumn(name = "upper_wear_id")
    private UpperWear upperWear;
}