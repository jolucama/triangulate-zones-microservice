/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.controller;

import com.microservices.zones.service.impl.TriangularZoneGeneratorServiceImpl;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author jlcm
 */
@ActiveProfiles("integrationTest")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZonesControllerIntegrationTest {

    //Fields
    private static final String BASE_URL = "/v1";

    @Autowired
    private TestRestTemplate restTemplate;
    
    private HttpEntity<?> request;
     
    @Before
    public void setUp() {
        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        request = new HttpEntity<>(requestHeaders);
    }
    
    @After
    public void tearDown() {
        restTemplate = null;
        request = null;
    }
    
    @Test
    public void testZones() throws Exception {

        //Invoke the API service
        ResponseEntity<List> response = restTemplate
                .exchange(BASE_URL + "/zones", HttpMethod.GET, request, List.class);

        //Verify
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    @Test
    public void testGenerateRandomZones() throws Exception {
        
        //Invoke the API service
        ResponseEntity<List> response = restTemplate
                .exchange(BASE_URL + "/zones/random/2", HttpMethod.GET, request, List.class);
        
        //Invoke the API service
        ResponseEntity<List> response2 = restTemplate
                .exchange(BASE_URL + "/zones/random/2", HttpMethod.GET, request, List.class);

        //Verify
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(HttpStatus.OK, response2.getStatusCode());
        
        Assert.assertEquals(response.getBody(), response2.getBody());
        
    }
}
