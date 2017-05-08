package com.microservices.zones.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 *
 * Class to represent a coordinate in this universe
 *
 * @author jlcardosa
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinate {

    @NotBlank
    private long id;

    @NotBlank
    @Range(min = -90, max = 90)
    private double latitude;

    @NotBlank
    @Range(min = -180, max = 180)
    private double longitude;

    public Coordinate() {
        
    }
    
    public Coordinate(double latitude, double longitude) {
        this.id = 0;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
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

    public void setId(long id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
