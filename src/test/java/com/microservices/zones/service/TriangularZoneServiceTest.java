/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.service;

import com.microservices.zones.exception.CoordinateOutOfZoneException;
import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.repository.TriangularZoneJsonRepository;
import com.microservices.zones.repository.TriangularZoneRepository;
import com.microservices.zones.service.impl.TriangularZoneServiceImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
public class TriangularZoneServiceTest {
    
    @Mock
    TriangularZoneRepository triangularZoneRepository;
    
    @InjectMocks
    TriangularZoneService triangularZoneService = new TriangularZoneServiceImpl();
    
    TriangularZone zone1;
    TriangularZone zone2;
            
    @Before
    public void setUp() {
        Coordinate coordinate1 = new Coordinate(0, 0);
        Coordinate coordinate2 = new Coordinate(0, 20);
        Coordinate coordinate3 = new Coordinate(10, 10);
        Coordinate coordinate4 = new Coordinate(20, 20);
        
        zone1 = new TriangularZone(1, coordinate1, coordinate2, coordinate3, "Test1");
        zone2 = new TriangularZone(2, coordinate2, coordinate3, coordinate4, "Test2");
    }
    
    @After
    public void tearDown() {
        
    }
    
    @Test
    public void getAllZonesTest(){
        List<TriangularZone> triangularZonesMock = Mockito.mock(ArrayList.class);
        Mockito.doReturn(3).when(triangularZonesMock).size();
        Mockito.doReturn(triangularZonesMock).when(triangularZoneRepository).findAll();
        
        assertEquals(3, triangularZoneService.getAllZones().size());
    }
    
    @Test
    public void getZonesByCoordinatesTest(){
        List<TriangularZone> triangularZones = new ArrayList();
        Coordinate coordinateToSearch = new Coordinate(5, 10);
        triangularZones.add(zone1);
        triangularZones.add(zone2);
        
        Mockito.doReturn(triangularZones).when(triangularZoneRepository).findAll();
        
        assertEquals(2, triangularZoneRepository.findAll().size());
        assertEquals(zone1, triangularZoneService.getZoneByCoordinate(coordinateToSearch));
    }
    
    @Test(expected = CoordinateOutOfZoneException.class)
    public void getZonesByCoordinatesOutOfZoneTest(){
        List<TriangularZone> triangularZones = new ArrayList();
        Coordinate coordinateToSearch = new Coordinate(50, 50);
        
        triangularZones.add(zone1);
        Mockito.doReturn(triangularZones).when(triangularZoneRepository).findAll();
        
        assertEquals(1, triangularZoneRepository.findAll().size());
        triangularZoneService.getZoneByCoordinate(coordinateToSearch);
    }
}
