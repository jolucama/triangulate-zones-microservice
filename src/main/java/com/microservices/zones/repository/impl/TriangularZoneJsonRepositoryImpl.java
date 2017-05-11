package com.microservices.zones.repository.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.repository.TriangularZoneJsonRepository;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jlcardosa on 11/05/2017.
 */
public class TriangularZoneJsonRepositoryImpl implements TriangularZoneJsonRepository {

    @Override
    public List loadFromJson(String fileName) throws IOException {

        ObjectMapper jsonMapper = new ObjectMapper();
        File jsonFile = new ClassPathResource(fileName).getFile();

        // deserialize contents of each file into an object of type
        List<TriangularZone> zoneList = jsonMapper.readValue(jsonFile, new TypeReference<List<TriangularZone>>(){});

        return zoneList;
    }
}
