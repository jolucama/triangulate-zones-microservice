package com.microservices.zones.controller;

import com.microservices.zones.entity.Coordinate;
import com.microservices.zones.entity.TriangularZone;
import com.microservices.zones.entity.Zones;
import com.microservices.zones.exception.CoordinateOutOfZoneException;
import com.microservices.zones.service.ZonesGenerator;
import com.microservices.zones.service.ZonesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@RestController
public class ZonesController {

    @Autowired
    private ZonesLoader zonesLoader;

    @Autowired
    private ZonesGenerator zonesGenerator;

    @RequestMapping(
            value = "/zones",
            method = RequestMethod.GET)
    public Zones zones() {
        return this.zonesLoader.getZones();
    }

    @RequestMapping(
            value = "/zones/random/{amount}",
            method = RequestMethod.GET)
    public List<TriangularZone> zonesRandomGenerated(@PathVariable int amount) {
        return this.zonesGenerator.generate(amount).getZones();
    }
    
    @RequestMapping(
            value = "/zone/find/coordinate",
            method = RequestMethod.GET)
    public ResponseEntity<TriangularZone> zoneByCoordinate(@RequestParam("lat") double latitude, @RequestParam("long") double longitude) {
        Coordinate coordinate = new Coordinate(latitude, longitude);
        Zones zones = this.zonesLoader.getZones();
        try { 
            TriangularZone triangularZone = zones.getZoneByCoordinate(coordinate);
            //Return message
            return new ResponseEntity<>(triangularZone, HttpStatus.OK);
        } catch (CoordinateOutOfZoneException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
