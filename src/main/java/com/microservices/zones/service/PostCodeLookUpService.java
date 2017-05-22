/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.service;

import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.PostCode;
import java.util.List;
import java.util.concurrent.Future;

/**
 *
 * @author josem
 */
public interface PostCodeLookUpService {
    
    public Future<List<PostCode>> findPostCode(Coordinate coordinate);
}
