package com.microservices.zones.entity;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

/**
 * Created by jlcardosa on 07/05/2017.
 */
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

    public TriangularZone(long id, Coordinate firstCoordinate, Coordinate secondCoordinate, Coordinate thirdCoordinate, String name) {
        this.id = id;
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
        this.thirdCoordinate = thirdCoordinate;
        this.name = name;
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

    public boolean coordinateIsInsideZone(@Validated Coordinate coordinate) {
        return this.zoneArea() == this.calculateArea(firstCoordinate, secondCoordinate, coordinate) +
                this.calculateArea(firstCoordinate, coordinate, thirdCoordinate) +
                this.calculateArea(coordinate, secondCoordinate, thirdCoordinate);
    }

    private double zoneArea() {
        return this.calculateArea(firstCoordinate, secondCoordinate, thirdCoordinate);
    }

    private double calculateArea(Coordinate A, Coordinate B, Coordinate C) {
        return Math.abs(
                A.getLatitude()*(B.getLongitude()-C.getLongitude()) +
                        B.getLatitude()*(C.getLongitude()-A.getLongitude())+
                        C.getLatitude()*(A.getLongitude()-B.getLongitude()))
                /2.0;
    }
}
