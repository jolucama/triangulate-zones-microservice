package com.microservices.zones.entity;

import com.microservices.zones.exception.CoordinateOutOfZoneException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlcardosa on 07/05/2017.
 */
public class Zones {

    private List<TriangularZone> zones;


    public Zones() {
        this.zones = new ArrayList<>();
    }

    public Zones(List<TriangularZone> zones) {
        this.zones = zones;
    }

    public void addZone(TriangularZone zone) {
        this.zones.add(zone);
    }

    /**
     *
     * @param coordinate
     * @return
     * @throws CoordinateOutOfZoneException
     */
    public TriangularZone getZoneByCoordinate(Coordinate coordinate) throws CoordinateOutOfZoneException {
        for (TriangularZone zone : zones) {
            if (zone.coordinateIsInsideZone(coordinate)) {
                return zone;
            }
        }
        throw new CoordinateOutOfZoneException();
    }

    public List<TriangularZone> getZones() {
        return this.zones;
    }
}
