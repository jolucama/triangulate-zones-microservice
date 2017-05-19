package com.microservices.zones.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;

/**
 *
 * Class to represent a coordinate in this universe
 *
 * @author jlcardosa
 */
@ApiModel(value="Coordinate", description="Model to represent a coordinate")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Coordinate {

    @NotBlank
    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    @NotBlank
    @Range(min = -90, max = 90)
    private double latitude;

    @NotBlank
    @Range(min = -180, max = 180)
    private double longitude;

    public Coordinate() {
        
    }
    
    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
