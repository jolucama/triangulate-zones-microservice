/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.service;

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
    
    @Before
    public void setUp() {
        
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
        
        Coordinate coordinate1 = new Coordinate(0, 0);
        Coordinate coordinate2 = new Coordinate(0, 20);
        Coordinate coordinate3 = new Coordinate(10, 10);
        Coordinate coordinate4 = new Coordinate(20, 20);
        Coordinate coordinateToSearch = new Coordinate(5, 5);
        
        TriangularZone triangularZone1 = new TriangularZone(1, coordinate1, coordinate2, coordinate3, "Test1");
        TriangularZone triangularZone2 = new TriangularZone(2, coordinate2, coordinate3, coordinate4, "Test2");
        triangularZones.add(triangularZone1);
        triangularZones.add(triangularZone2);
        
        Mockito.doReturn(triangularZones).when(triangularZoneRepository).findAll();
        assertEquals(triangularZone1, triangularZoneService.getZoneByCoordinate(coordinateToSearch));
    }
}
