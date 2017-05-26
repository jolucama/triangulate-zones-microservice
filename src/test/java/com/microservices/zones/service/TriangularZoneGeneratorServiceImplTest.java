/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.service;

import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.service.impl.TriangularZoneGeneratorServiceImpl;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jlcardosa
 */
@ActiveProfiles("unitTest")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TriangularZoneGeneratorServiceImplTest {
    
    TriangularZoneGeneratorService triangularZoneGeneratorService;
            
    @Before
    public void setUp() {
        triangularZoneGeneratorService = new TriangularZoneGeneratorServiceImpl();
    }
    
    @After
    public void tearDown() {
        triangularZoneGeneratorService = null;
    }
    
    @Test
    public void generateTest(){
        List triangularZones = triangularZoneGeneratorService.generate(5);
        
        assertEquals(triangularZones.size(), 5);
        assertTrue(triangularZones.get(0) instanceof TriangularZone);
    }
    
    @Test
    public void resetGenerationCacheTest() {
        List triangularZones1 = triangularZoneGeneratorService.generate(5);
        triangularZoneGeneratorService.resetGeneration();
        List triangularZones2 = triangularZoneGeneratorService.generate(5);
        assertNotSame(triangularZones1, triangularZones2);
    }
}
