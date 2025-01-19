package com.appsolve.wearther_backend.init_data.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "weather_lower_wear")
public class WeatherLowerWear {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weather_lower_wear_id")
    private Long id;

    @ManyToOne @JoinColumn(name="weather_id")
    private Weather weather;

    @ManyToOne @JoinColumn(name = "lower_wear_id")
    private LowerWear lowerWear;
}
