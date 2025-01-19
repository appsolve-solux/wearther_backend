package com.appsolve.wearther_backend.init_data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder @Getter
@Table(name="location_info")
public class LocationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="code")
    private String id;

    @Column(name="level1")
    private String level1;

    @Column(name = "level2")
    private String level2;

    @Column(name = "level3")
    private String level3;

    @Column(name = "grid_x")
    private String gridX;

    @Column(name = "grid_y")
    private String gridY;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;
}