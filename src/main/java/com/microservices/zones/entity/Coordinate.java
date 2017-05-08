package com.microservices.zones.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * Class to represent a coordinate in this universe
 *
 * @author jlcardosa
 */
public class Coordinate {

    @NotBlank
    private final long id;

    @NotBlank
    @Range(min = -90, max = 90)
    private final double latitude;

    @NotBlank
    @Range(min = -180, max = 180)
    private final double longitude;

    public Coordinate(long id, double latitude, double longitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
