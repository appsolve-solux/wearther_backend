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
@Table(name = "weather_other_wear")
public class WeatherOtherWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_other_wear_id")
    private Long id;

    @ManyToOne @JoinColumn(name="weather_id")
    private Weather weather;

    @ManyToOne @JoinColumn(name = "other_wear_id")
    private OtherWear otherWear;
}
