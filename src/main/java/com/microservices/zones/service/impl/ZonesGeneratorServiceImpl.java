package com.microservices.zones.service.impl;

import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.model.Zones;
import com.microservices.zones.service.ZonesGeneratorService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jlcardosa on 08/05/2017.
 */
@Service
public class ZonesGeneratorServiceImpl implements ZonesGeneratorService {

    private Zones zones;
    private AtomicLong counter = new AtomicLong();

    public Zones generate(int count) {
        zones = new Zones();
        for (int i = 0; i < count; i++) {
            zones.addZone(this.generateTriangularZone());
        }
        return zones;
    }

    private TriangularZone generateTriangularZone() {
        return new TriangularZone(
                counter.incrementAndGet(),
                this.generateRamdomCoordinate(),
                this.generateRamdomCoordinate(),
                this.generateRamdomCoordinate(),
                "Zone Test " + counter.get());
    }

    private Coordinate generateRamdomCoordinate() {
        double latitude = Math.random()*90;
        double longitude = Math.random()*180;

        return new Coordinate(counter.incrementAndGet(), latitude, longitude);
    }

}
