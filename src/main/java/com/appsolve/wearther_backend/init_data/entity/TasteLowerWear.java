package com.appsolve.wearther_backend.init_data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name = "taste_lower_wear")
public class TasteLowerWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taste_lower_wear_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taste_id")
    private Taste taste;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lower_wear_id")
    private LowerWear lowerWear;
}
