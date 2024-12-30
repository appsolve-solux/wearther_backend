package com.appsolve.wearther_backend.closet.entity;

import com.appsolve.wearther_backend.init_data.entity.LowerWear;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name="closet_lower")
public class ClosetLower {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closet_lower_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "closet_id")
    private Closet closet;

    @ManyToOne
    @JoinColumn(name = "lower_wear_id")
    private LowerWear lowerWear;
}
