package com.microservices.zones.service.impl;

import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import com.microservices.zones.service.TriangularZoneGeneratorService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * Created by jlcardosa on 08/05/2017.
 */

@Component
@Service
public class TriangularZoneGeneratorServiceImpl implements TriangularZoneGeneratorService {

    private final AtomicLong counter = new AtomicLong();

    @Override
    @Cacheable(value = "zones", key = "#count")
    public List<TriangularZone> generate(int count) {
        List zones = new ArrayList();
        for (int i = 0; i < count; i++) {
            zones.add(this.generateTriangularZone());
        }
        return zones;
    }
    
    @CacheEvict(value = "zones", allEntries = true)
    @Override
    public void resetGeneration() {
        // Intentionally blank
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
