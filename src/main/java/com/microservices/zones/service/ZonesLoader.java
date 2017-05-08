package com.microservices.zones.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.zones.entity.TriangularZone;
import com.microservices.zones.entity.Zones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jlcardosa on 07/05/2017.
 */
//@Component
public class ZonesLoader {

    private static final String jsonFileName = "zones.json";
    private Zones zones;

    //@Autowired
    public ZonesLoader() {

    }

    //@PostConstruct
    public void init() throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        File jsonFile = new ClassPathResource(ZonesLoader.jsonFileName).getFile();

        // deserialize contents of each file into an object of type
        List<TriangularZone> zones = jsonMapper.readValue(jsonFile, new TypeReference<List<TriangularZone>>() {});
        this.zones = new Zones(zones);
    }

    public Zones getZones() {
        return this.zones;
    }
}
