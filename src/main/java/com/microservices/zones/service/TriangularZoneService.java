package com.microservices.zones.service;

import com.microservices.zones.exception.CoordinateOutOfZoneException;
import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;

import java.util.List;

/**
 * Created by jlcardosa on 09/05/2017.
 */
public interface TriangularZoneService {

    TriangularZone getZoneByCoordinate(Coordinate coordinate) throws CoordinateOutOfZoneException;

    List getAllZones();
}
