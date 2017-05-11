package com.microservices.zones.controller;

import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.service.TriangularZoneService;
import com.microservices.zones.service.impl.TriangularZoneGeneratorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@Api(description = "Operations about zones")
@RestController
@RequestMapping("/v1")
public class ZonesController {

    @Autowired
    private TriangularZoneService triangularZoneService;

    @Autowired
    private TriangularZoneGeneratorServiceImpl zonesGenerator;

    @RequestMapping(
            value = "/zones",
            method = RequestMethod.GET)
    public List<TriangularZone> zones() {
        return this.triangularZoneService.getAllZones();
    }

    @ApiOperation(value = "Generate a number of zones randomly",
                  notes = "Generate a number of zones randomly",
                  response = List.class,
                  responseContainer = "List")
    @ApiResponses(value = {
                  @ApiResponse(code = 200, message = "Set of zones")
                  })
    @RequestMapping(
            value = "/zones/random/{amount}",
            method = RequestMethod.GET)
    public List<TriangularZone> zonesRandomGenerated(
            @ApiParam(value = "Number of zones generated randomly", required = true) @PathVariable int amount) {
        return this.zonesGenerator.generate(amount);
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
            value = "/zones/search",
            method = RequestMethod.GET)
    public TriangularZone zoneByCoordinate(@RequestParam("lat") double latitude, @RequestParam("long") double longitude) {
        Coordinate coordinate = new Coordinate(latitude, longitude);

        return this.triangularZoneService.getZoneByCoordinate(coordinate);
    }
}
