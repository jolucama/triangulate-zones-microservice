package com.microservices.zones.repository;

import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;

/**
 * Created by jlcardosa on 11/05/2017.
 */
public interface TriangularZoneRepositoryCustom {

    public TriangularZone findByCoordinate(Coordinate coordinate);
}
