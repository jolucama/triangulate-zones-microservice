package com.microservices.zones.exception;

/**
 * Created by jlcardosa on 07/05/2017.
 */
public class AsynchronousException extends RuntimeException {

    public AsynchronousException(String message){
        super(message);
    }
}
