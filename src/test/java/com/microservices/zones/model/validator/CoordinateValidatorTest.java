/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.model.validator;

import com.microservices.zones.model.Coordinate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.MockitoAnnotations;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author josem
 */
public class CoordinateValidatorTest {
    
    private Validator coordinateValidator;
    
    private BindException errors;
    
    @Before
    public void setUp() {
        this.coordinateValidator = new CoordinateValidator();
    }
    
    @Test public void supportsTest() {
        assertTrue(coordinateValidator.supports(Coordinate.class));
        assertFalse(coordinateValidator.supports(Object.class));
    }

    @Test
    public void validateTest() {
        Coordinate coordinate = new Coordinate(10, 10);
        errors = new BindException(coordinate, "coordinate");
        ValidationUtils.invokeValidator(coordinateValidator, coordinate, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validateOutOfRangeCoordinateTest() {
        Coordinate coordinate = new Coordinate(300,300);
        errors = new BindException(coordinate, "coordinate");
        ValidationUtils.invokeValidator(coordinateValidator, coordinate, errors);
        assertEquals(2, errors.getErrorCount());
        assertEquals(1, errors.getFieldErrorCount("latitude"));
        assertEquals("latitude.out.range", errors.getFieldError("latitude").getCode());
        assertEquals(1, errors.getFieldErrorCount("longitude"));
        assertEquals("longitude.out.range", errors.getFieldError("longitude").getCode());
    }
}
