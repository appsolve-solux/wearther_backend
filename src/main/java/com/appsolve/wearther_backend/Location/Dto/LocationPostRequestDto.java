package com.appsolve.wearther_backend.Location.Dto;

public class LocationPostRequestDto {
    private Long locationId;
    private Long userNumber;
    private String locationInfo;
    private Integer locationIndex;

    // Getter, Setter
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
