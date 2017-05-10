/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.utils;

import com.microservices.zones.model.Coordinate;

/**
 *
 * @author josem
 */
public abstract class AreaCalculator {

    public static double calculateTriangularArea(Coordinate a, Coordinate b, Coordinate c) {
        return Math.abs(a.getLatitude()*(b.getLongitude()-c.getLongitude()) +
                        b.getLatitude()*(c.getLongitude()-a.getLongitude())+
                        c.getLatitude()*(a.getLongitude()-b.getLongitude()))
                /2.0;
    }
}
