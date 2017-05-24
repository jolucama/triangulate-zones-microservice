package com.microservices.zones.service;

import java.util.List;

/**
 * Created by jlcardosa on 09/05/2017.
 */
public interface TriangularZoneGeneratorService {

    public List generate(int count);
    
    public void resetGeneration();
}
