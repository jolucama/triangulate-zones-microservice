package com.microservices.zones.repository;

import com.microservices.zones.model.Coordinate;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jlcardosa on 09/05/2017.
 */
public interface CoordinateRepository extends MongoRepository<Coordinate, Long>{

}
