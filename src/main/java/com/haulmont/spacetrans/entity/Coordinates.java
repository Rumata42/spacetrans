package com.haulmont.spacetrans.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;
import io.jmix.core.metamodel.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@JmixEntity(name = "st_Coordinates")
@Embeddable
public class Coordinates {
    @NumberFormat(pattern = "#.######")
    @Column(name = "LATITUDE")
    private Double latitude;

    @NumberFormat(pattern = "#.######")
    @Column(name = "LONGITUDE")
    private Double longitude;

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
