package com.microservices.zones.repository;

import com.microservices.zones.model.TriangularZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class that handles database operations for TriangularZone
 */
@Repository
public interface TriangularZoneRepository extends JpaRepository<TriangularZone, Long>, TriangularZoneRepositoryCustom {

}
