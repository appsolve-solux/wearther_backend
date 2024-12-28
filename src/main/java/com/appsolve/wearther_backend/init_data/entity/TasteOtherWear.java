package com.appsolve.wearther_backend.init_data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "taste_other_wear")
public class TasteOtherWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taste_other_wear_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taste_id")
    private Taste taste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "other_wear_id")
    private OtherWear otherWear;
}
