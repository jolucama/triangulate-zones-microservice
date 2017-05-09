package com.microservices.zones.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.model.Zones;
import com.microservices.zones.service.ZonesLoaderService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@Service
public class ZonesLoaderServiceImpl implements ZonesLoaderService {

    private static final String jsonFileName = "zones.json";
    private Zones zones;

    public ZonesLoaderServiceImpl() {
        
    }
    
    @PostConstruct
    public void init() throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        File jsonFile = new ClassPathResource(ZonesLoaderServiceImpl.jsonFileName).getFile();
        
        // deserialize contents of each file into an object of type
        List<TriangularZone> zoneList = jsonMapper.readValue(jsonFile, new TypeReference<List<TriangularZone>>(){});
        this.zones = new Zones(zoneList);
    }

    public Zones getZones() {
        return this.zones;
    }
}
