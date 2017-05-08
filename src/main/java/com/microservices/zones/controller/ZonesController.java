package com.microservices.zones.controller;

import com.microservices.zones.entity.Coordinate;
import com.microservices.zones.entity.TriangularZone;
import com.microservices.zones.entity.Zones;
import com.microservices.zones.service.ZonesGenerator;
import com.microservices.zones.service.ZonesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@RestController
public class ZonesController {

    //@Autowired
    private ZonesLoader zonesLoader;

    @Autowired
    private ZonesGenerator zonesGenerator;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Test";
    }

    @RequestMapping("/zones")
    public Zones zones() {
        return this.zonesLoader.getZones();
    }


    @RequestMapping("/zones/random/{amount}")
    public Zones zonesRandomGenerated(@PathVariable int amount) {
        return this.zonesGenerator.generate(amount);
    }
}
