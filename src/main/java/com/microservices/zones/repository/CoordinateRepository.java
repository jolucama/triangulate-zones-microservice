package com.microservices.zones.repository;

import com.microservices.zones.model.Coordinate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jlcardosa on 09/05/2017.
 */
public interface CoordinateRepository extends CrudRepository<Coordinate, Long>{

}
