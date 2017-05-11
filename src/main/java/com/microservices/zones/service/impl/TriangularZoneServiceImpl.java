/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.zones.model.TriangularZone;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.springframework.core.io.ClassPathResource;

/**
 *
 * @author josem
 */
public class TriangularZoneServiceImpl {
    
    
    
    
    public List loadFromJson(String fileName) throws IOException {
        
        ObjectMapper jsonMapper = new ObjectMapper();
        File jsonFile = new ClassPathResource(fileName).getFile();
        
        // deserialize contents of each file into an object of type
        List<TriangularZone> zoneList = jsonMapper.readValue(jsonFile, new TypeReference<List<TriangularZone>>(){});
        
        return zoneList;
    }
}
