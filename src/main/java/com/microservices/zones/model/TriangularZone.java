package com.microservices.zones.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "triangularZone")
public class TriangularZone {

    @NotBlank
    @Id
    private String id;

    @NotBlank
    private Coordinate firstCoordinate;

    @NotBlank
    private Coordinate secondCoordinate;

    @NotBlank
    private Coordinate thirdCoordinate;

    private String name;

    public TriangularZone() {}

    public TriangularZone(Coordinate firstCoordinate, Coordinate secondCoordinate, Coordinate thirdCoordinate, String name) {
        this.firstCoordinate = firstCoordinate;
        this.secondCoordinate = secondCoordinate;
        this.thirdCoordinate = thirdCoordinate;
        this.name = name;
    }

    public String getId() {
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

    public void setId(String id) {
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

}
