package com.microservices.zones.service;

import com.microservices.zones.entity.Coordinate;
import com.microservices.zones.entity.TriangularZone;
import com.microservices.zones.entity.Zones;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by jlcardosa on 08/05/2017.
 */
@Service
public class ZonesGenerator {

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
