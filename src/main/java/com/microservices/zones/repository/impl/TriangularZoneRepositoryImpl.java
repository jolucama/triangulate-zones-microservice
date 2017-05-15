package com.microservices.zones.repository.impl;

import com.microservices.zones.exception.DatabaseException;
import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.repository.TriangularZoneRepository;
import com.microservices.zones.repository.TriangularZoneRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by jlcardosa on 11/05/2017.
 */
@Repository
public class TriangularZoneRepositoryImpl implements TriangularZoneRepositoryCustom {

    //The LOG
    private static final Logger LOGGER = LoggerFactory.getLogger(TriangularZoneRepositoryImpl.class);
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieves league from uuid given
     * @param coordinate Coordinate
     * @return
     */
    @Override
    public TriangularZone findByCoordinate(Coordinate coordinate) {
        try {

            //Create the sql
            String sql =
                    "SELECT * " +
                    "FROM triangular_zone " +
                    "where coordinate_1_id = ? OR coordinate_2_id = ? OR coordinate_3_id = ?";
            
            
            LOGGER.debug("Calling TriangularZoneRepository findByCoordinate");

            //Create list
            TriangularZone triangularZone = jdbcTemplate.queryForObject(sql, (result, rowNum) -> {

                TriangularZone tz = new TriangularZone();

                tz.setId(result.getLong("id"));
                tz.setName(result.getString("name"));
                tz.setFirstCoordinate((Coordinate) result.getObject("coordinate_1_id"));
                tz.setSecondCoordinate((Coordinate) result.getObject("coordinate_2_id"));
                tz.setThirdCoordinate((Coordinate) result.getObject("coordinate_3_id"));

                return tz;
            }, coordinate.getId());

            LOGGER.error("Returning the triangular zone with id [{}]", triangularZone.getId());
            return triangularZone;
        }
        catch (EmptyResultDataAccessException e){
            LOGGER.error("EmptyResultDataAccessException exception while finding a zone", e);
            throw new ResourceNotFoundException("Triangular Zone not found, please ensure that your coordinate is correct");
        }
        catch (Exception e) {
            LOGGER.error("Unknown exception while finding a zone", e);
            throw new DatabaseException("Unknown exception while retrieving league from uuid given", e);
        }
    }
}
