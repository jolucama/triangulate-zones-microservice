package com.microservices.zones.repository.impl;

import com.microservices.zones.exception.DatabaseException;
import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.repository.TriangularZoneRepositoryCustom;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by jlcardosa on 11/05/2017.
 */
@Repository
public class TriangularZoneRepositoryImpl implements TriangularZoneRepositoryCustom {

    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(TriangularZoneRepositoryImpl.class);
    
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Retrieves zone from a coordinate give
     * @param coordinate Coordinate
     * @return
     */
    @Override
    public TriangularZone findByCoordinate(Coordinate coordinate) {
        try {
            Query query = new Query();
            query.addCriteria(
                Criteria.where("firstCoordinate").is(coordinate).orOperator(
                    Criteria.where("secondCoordinate").is(coordinate).orOperator(
                        Criteria.where("thirdCoordinate").is(coordinate)
                    )
                )
            );
            
            List<TriangularZone> triangularZones = mongoTemplate.find(query, TriangularZone.class);
            TriangularZone triangularZone = triangularZones.get(0);

            LOGGER.error("Returning the triangular zone with id [{}]", triangularZone.getId());
            return triangularZone;
        }
        catch (Exception e) {
            LOGGER.error("Unknown exception while finding a zone", e);
            throw new DatabaseException("Unknown exception while retrieving triangularZone from coordinate give", e);
        }
    }
}
