package com.microservices.zones.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * Class to represent a coordinate in this universe
 *
 * @author jlcardosa
 */
@ApiModel(value="Coordinate", description="Model to represent a coordinate")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Coordinate {

    @NotBlank
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
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
