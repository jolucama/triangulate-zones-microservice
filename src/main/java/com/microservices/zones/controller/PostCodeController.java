package com.microservices.zones.controller;

import com.microservices.zones.exception.AsynchronousException;
import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.PostCode;
import com.microservices.zones.model.TriangularZone;
import com.microservices.zones.service.PostCodeLookUpService;
import com.microservices.zones.service.TriangularZoneService;
import com.microservices.zones.service.impl.TriangularZoneGeneratorServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.http.ResponseEntity;

/**
 * Created by jlcardosa on 07/05/2017.
 */
@Api(description = "Operations about zones")
@RestController
@RequestMapping("/v1")
public class PostCodeController {

    @Autowired
    private PostCodeLookUpService postcodeLookUpService;

    @ApiOperation(value = "Determinate a zone by a given coordinate",
                  notes = "Determinate a zone by a given coordinate"
                 )
    @ApiResponses(value = {
                    @ApiResponse(code = 200, message = "Zone that has been found"),
                    @ApiResponse(code = 204, message = "No zone found")
                 })
    @RequestMapping(
            value = "/postcode",
            method = RequestMethod.GET)
    public List<PostCode> postcodeByCoordinate(@RequestParam("lat") double latitude, @RequestParam("long") double longitude) {
        try {
            Coordinate coordinate = new Coordinate(latitude, longitude);
            
            // Testing asynchronous
            Future<List<PostCode>> result = this.postcodeLookUpService.findPostCode(coordinate);
            
            while (!result.isDone()) {
                Thread.sleep(10);
            }
            
            return result.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(PostCodeController.class.getName()).log(Level.SEVERE, null, ex);
            throw new AsynchronousException(ex.getMessage());
        } catch (ExecutionException ex) {
            throw new AsynchronousException(ex.getMessage());
        }
    }
}
