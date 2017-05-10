package com.microservices.zones.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservices.zones.utils.AreaCalculator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class TriangularZone {

    @NotBlank
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_1_id")
    private Coordinate firstCoordinate;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_2_id")
    private Coordinate secondCoordinate;

    @NotBlank
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coordinate_3_id")
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

    public boolean isCoordinateInsideZone(@Validated Coordinate coordinate) {
        return Math.abs(this.zoneArea() - AreaCalculator.calculateTriangularArea(firstCoordinate, secondCoordinate, coordinate) +
                AreaCalculator.calculateTriangularArea(firstCoordinate, coordinate, thirdCoordinate) +
                AreaCalculator.calculateTriangularArea(coordinate, secondCoordinate, thirdCoordinate)) < .01;
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
        return AreaCalculator.calculateTriangularArea(firstCoordinate, secondCoordinate, thirdCoordinate);
    }
}
