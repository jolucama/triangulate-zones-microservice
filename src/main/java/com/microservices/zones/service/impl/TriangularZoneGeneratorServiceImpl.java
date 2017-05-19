package com.microservices.zones.service.impl;

import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.microservices.zones.service.TriangularZoneGeneratorService;

/**
 * Created by jlcardosa on 08/05/2017.
 */
@Service
public class TriangularZoneGeneratorServiceImpl implements TriangularZoneGeneratorService {
    
    @Override
    public List<TriangularZone> generate(int count) {
        List zones = new ArrayList();
        for (int i = 0; i < count; i++) {
            zones.add(this.generateTriangularZone());
        }
        return zones;
    }

    private TriangularZone generateTriangularZone() {
        return new TriangularZone(
                this.generateRamdomCoordinate(),
                this.generateRamdomCoordinate(),
                this.generateRamdomCoordinate(),
                "Zone Test ");
    }

    private Coordinate generateRamdomCoordinate() {
        double latitude = Math.random()*90;
        double longitude = Math.random()*180;

        return new Coordinate(latitude, longitude);
    }
}
