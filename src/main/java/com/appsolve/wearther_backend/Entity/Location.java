package com.appsolve.wearther_backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "location")
public class Location {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;  // Primary key: location_id
    private Long memberId;  // member_id
    private String locationInfo;  // location_info (문자열 배열)
    private Integer locationIndex;  // location_index

}