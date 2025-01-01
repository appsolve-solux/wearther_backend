package com.appsolve.wearther_backend.Location.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "location")
public class LocationEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;  // Primary key: location_id
    private Long userId;  // user_id
    private String locationInfo;  // location_info (문자열 배열)
    private Integer locationIndex;  // location_index

}