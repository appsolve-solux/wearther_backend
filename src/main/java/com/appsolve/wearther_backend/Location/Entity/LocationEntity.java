package com.appsolve.wearther_backend.Location.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "location")
public class LocationEntity {

    @Id
    private Long locationId;  // Primary key: location_id
    private Long userNumber;  // user_number
    private String locationInfo;  // location_info (문자열 배열)
    private Integer locationIndex;  // location_index

}