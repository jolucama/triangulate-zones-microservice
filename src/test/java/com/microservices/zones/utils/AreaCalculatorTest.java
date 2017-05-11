/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.utils;

import com.microservices.zones.model.Coordinate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test class
 * @author jlcardosa
 */
@ActiveProfiles("unitTest")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AreaCalculatorTest {

    private Coordinate coordinate1;
    private Coordinate coordinate2;
    private Coordinate coordinate3;

    @Before
    public void setUp() {
        coordinate1 = new Coordinate(1, 20, 20);
        coordinate2 = new Coordinate(1, 30, 30);
        coordinate3 = new Coordinate(1, 20, 30);
    }

    @Test
    public void calculateTriangularAreaTest() {
        assertEquals(
                50,
                AreaCalculator.calculateTriangularArea(coordinate1, coordinate2, coordinate3),
                0.1
        );
    }

    @Test
    public void calculateTriangularAreaEmptyTest() {
        coordinate3 = new Coordinate(1, 40, 40);
        assertEquals(
                0,
                AreaCalculator.calculateTriangularArea(coordinate1, coordinate2, coordinate3),
                0.1
        );
    }
}
