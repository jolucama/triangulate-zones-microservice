package com.microservices.zones.service;

import com.microservices.zones.model.Zones;

/**
 * Created by jlcardosa on 09/05/2017.
 */
public interface ZonesGeneratorService {

    public Zones generate(int count);

}
