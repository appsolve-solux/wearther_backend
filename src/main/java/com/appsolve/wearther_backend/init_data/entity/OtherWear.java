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
@Builder
@Table(name="other_wear")
public class OtherWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="other_wear_id")
    private Long id;

    @Column(name = "other_wear_name") @NotNull
    private String name;

    @OneToMany(mappedBy = "otherWear", fetch = LAZY)
    private List<TasteOtherWear> tasteOtherWears = new ArrayList<>();
}