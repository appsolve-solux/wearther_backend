package com.appsolve.wearther_backend.Location.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "location")
public class LocationEntity {

    @Id
    private Long locationId;  // Primary key: location_id

    private Long userNumber;  // user_number

    private String locationInfo;  // location_info (문자열 배열)

    private Integer locationIndex;  // location_index

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public String getLocationInfo() {
        return locationInfo;
    }

    public void setLocationInfo(String locationInfo) {
        this.locationInfo = locationInfo;
    }

    public Integer getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(Integer locationIndex) {
        this.locationIndex = locationIndex;
    }
}