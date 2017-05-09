package com.microservices.zones.controller;

import com.microservices.zones.entity.Coordinate;
import com.microservices.zones.entity.TriangularZone;
import com.microservices.zones.entity.Zones;
import com.microservices.zones.exception.CoordinateOutOfZoneException;
import com.microservices.zones.service.ZonesGenerator;
import com.microservices.zones.service.ZonesLoader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@Api(description = "Operations about zones")
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

    @ApiOperation(value = "Generate a number of zones randomly",
                  notes = "Generate a number of zones randomly",
                  response = Zones.class,
                  responseContainer = "List")
    @ApiResponses(value = {
                  @ApiResponse(code = 200, message = "Set of zones")
                  })
    @RequestMapping(
            value = "/zones/random/{amount}",
            method = RequestMethod.GET)
    public List<TriangularZone> zonesRandomGenerated(
            @ApiParam(value = "Number of zones generated ramdonly", required = true) @PathVariable int amount) {
        return this.zonesGenerator.generate(amount).getZones();
    }

    @ApiOperation(value = "Determinate a zone by a given coordinate",
                  notes = "Determinate a zone by a given coordinate",
                  response = TriangularZone.class
                 )
    @ApiResponses(value = {
                    @ApiResponse(code = 200, message = "Zone that has been found"),
                    @ApiResponse(code = 204, message = "No zone found")
                 })
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
