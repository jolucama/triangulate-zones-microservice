package com.microservices.zones.repository;

import com.microservices.zones.model.TriangularZone;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Class that handles database operations for TriangularZone
 */
public interface TriangularZoneRepository extends MongoRepository<TriangularZone, Long>, TriangularZoneRepositoryCustom {

}
