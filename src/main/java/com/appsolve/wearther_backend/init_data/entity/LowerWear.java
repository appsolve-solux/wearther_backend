package com.appsolve.wearther_backend.init_data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name="lower_wear")
public class LowerWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lower_wear_id")
    private Long id;

    @Column(name = "lower_wear_name", length = 100) @NotNull
    private String name;

    @Column(name="lower_wear_type") @NotNull
    private int type;

    @OneToMany(mappedBy = "lowerWear", fetch = LAZY)
    private List<TasteLowerWear> tasteLowerWears = new ArrayList<>();

    @OneToMany(mappedBy = "lowerWear", fetch = LAZY)
    private List<WeatherLowerWear> weathers = new ArrayList<>();
}
