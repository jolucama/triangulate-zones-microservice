package com.microservices.zones.exception;

/**
 * Created by jlcardosa on 07/05/2017.
 */
public class CoordinateOutOfZoneException extends RuntimeException {

    public CoordinateOutOfZoneException(String message){
        super(message);
    }
}
