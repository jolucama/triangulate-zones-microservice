package com.microservices.zones.repository;

import java.io.IOException;
import java.util.List;

/**
 * Created by jlcardosa on 11/05/2017.
 */
public interface TriangularZoneJsonRepository {

    List loadFromJson(String fileName) throws IOException;

}
