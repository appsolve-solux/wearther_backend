package com.appsolve.wearther_backend.init_data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "taste_upper_wear")
public class TasteUpperWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taste_upper_wear_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "taste_id")
    private Taste taste;

    @ManyToOne
    @JoinColumn(name = "upper_wear_id")
    private UpperWear upperWear;
}
