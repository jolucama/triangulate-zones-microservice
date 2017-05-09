package com.microservices.zones.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TriangularZone {

    @NotBlank
    private long id;

    @NotBlank
    private Coordinate firstCoordinate;

    @NotBlank
    private Coordinate secondCoordinate;

    @NotBlank
    private Coordinate thirdCoordinate;

    private String name;

    public TriangularZone() {}

    public TriangularZone(long id, Coordinate firstCoordinate, Coordinate secondCoordinate, Coordinate thirdCoordinate, String name) {
        this.id = id;
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
        this.thirdCoordinate = thirdCoordinate;
        this.name = name;
    }

    public boolean coordinateIsInsideZone(@Validated Coordinate coordinate) {
        return Math.abs(this.zoneArea() - TriangularZone.calculateArea(firstCoordinate, secondCoordinate, coordinate) +
                TriangularZone.calculateArea(firstCoordinate, coordinate, thirdCoordinate) +
                TriangularZone.calculateArea(coordinate, secondCoordinate, thirdCoordinate)) < .01;
    }

    public static double calculateArea(Coordinate a, Coordinate b, Coordinate c) {
        return Math.abs(a.getLatitude()*(b.getLongitude()-c.getLongitude()) +
                        b.getLatitude()*(c.getLongitude()-a.getLongitude())+
                        c.getLatitude()*(a.getLongitude()-b.getLongitude()))
                /2.0;
    }

    public long getId() {
        return id;
    }

    public Coordinate getFirstCoordinate() {
        return firstCoordinate;
    }

    public Coordinate getSecondCoordinate() {
        return secondCoordinate;
    }

    public Coordinate getThirdCoordinate() {
        return thirdCoordinate;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstCoordinate(Coordinate firstCoordinate) {
        this.firstCoordinate = firstCoordinate;
    }

    public void setSecondCoordinate(Coordinate secondCoordinate) {
        this.secondCoordinate = secondCoordinate;
    }

    public void setThirdCoordinate(Coordinate thirdCoordinate) {
        this.thirdCoordinate = thirdCoordinate;
    }

    public void setName(String name) {
        this.name = name;
    }

    private double zoneArea() {
        return TriangularZone.calculateArea(firstCoordinate, secondCoordinate, thirdCoordinate);
    }
}
