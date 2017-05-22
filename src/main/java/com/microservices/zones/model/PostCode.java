/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.model;

/**
 *
 * @author josem
 */
public class PostCode {
    
    private String postcode;
    
    private String country;
    
    private String region;
    
    private long eastings;
    
    private long northindgs;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public long getEastings() {
        return eastings;
    }

    public void setEastings(long eastings) {
        this.eastings = eastings;
    }

    public long getNorthindgs() {
        return northindgs;
    }

    public void setNorthindgs(long northindgs) {
        this.northindgs = northindgs;
    }
}
