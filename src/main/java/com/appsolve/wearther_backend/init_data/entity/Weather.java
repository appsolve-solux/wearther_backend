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
@Table(name="weather")
public class Weather {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="weather_id")
    private long id;

    @Column(name="weather_name", length = 100) @NotNull
    private String name;

    @Column(name = "weather_description")
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "weather", fetch = LAZY)
    private List<WeatherUpperWear> weatherUpperWearList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "weather", fetch = LAZY)
    private List<WeatherLowerWear> lowerWears = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "weather", fetch = LAZY)
    private List<WeatherOtherWear> otherWears = new ArrayList<>();
}