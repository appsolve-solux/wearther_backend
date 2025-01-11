package com.appsolve.wearther_backend.init_data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name="taste")
public class Taste {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="taste_id")
    private Long id;

    @Column(name="taste_name", length=100) @NotNull
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "taste", fetch = LAZY)
    private List<TasteUpperWear> upperWears = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "taste", fetch = FetchType.LAZY)
    private List<TasteLowerWear> lowerWears = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "taste", fetch = LAZY)
    private List<TasteOtherWear> otherWears = new ArrayList<>();
}