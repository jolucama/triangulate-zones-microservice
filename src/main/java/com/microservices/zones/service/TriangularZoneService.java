package com.microservices.zones.service;

import com.microservices.zones.model.Zones;
import java.util.List;

/**
 * Created by jlcardosa on 09/05/2017.
 */
public interface TriangularZoneService {

    public List loadFromJson();
    
    public Zones getZones();
}
