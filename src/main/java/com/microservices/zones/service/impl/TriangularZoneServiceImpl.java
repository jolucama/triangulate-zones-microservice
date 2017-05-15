/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.zones.exception.CoordinateOutOfZoneException;
import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.microservices.zones.repository.TriangularZoneRepository;
import com.microservices.zones.service.TriangularZoneService;
import com.microservices.zones.utils.AreaCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author josem
 */
@Service
public class TriangularZoneServiceImpl implements TriangularZoneService {

    @Autowired
    private TriangularZoneRepository triangularZoneRepository;

    /**
     * Get one zone depending on a coordinate.
     * If the coordinate is inside return the zone, otherwise throw an exception
     *
     * @param coordinate
     * @return
     * @throws CoordinateOutOfZoneException
     */
    @Override
    public TriangularZone getZoneByCoordinate(Coordinate coordinate) throws CoordinateOutOfZoneException {
        List<TriangularZone> triangularZones = triangularZoneRepository.findAll();
        for (TriangularZone zone : triangularZones) {
            if (this.isCoordinateInsideTriangularZone(coordinate, zone) == true) {
                return zone;
            }
        }
        throw new CoordinateOutOfZoneException("The given coordinate is outside of all the zones");
    }

    @Override
    public List<TriangularZone> getAllZones() {
        return triangularZoneRepository.findAll();
    }

    private boolean isCoordinateInsideTriangularZone(@Validated Coordinate coordinate, TriangularZone triangularZone) {
        return Math.abs(this.getTriangularZoneArea(triangularZone) -
                AreaCalculator.calculateTriangularArea(triangularZone.getFirstCoordinate(), triangularZone.getSecondCoordinate(), coordinate) -
                AreaCalculator.calculateTriangularArea(triangularZone.getFirstCoordinate(), coordinate, triangularZone.getThirdCoordinate()) -
                AreaCalculator.calculateTriangularArea(coordinate, triangularZone.getSecondCoordinate(), triangularZone.getThirdCoordinate())) < .01;
    }

    private double getTriangularZoneArea(TriangularZone triangularZone) {
        return AreaCalculator.calculateTriangularArea(
                triangularZone.getFirstCoordinate(),
                triangularZone.getSecondCoordinate(),
                triangularZone.getThirdCoordinate());
    }
}
