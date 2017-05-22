/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microservices.zones.service.impl;

import com.microservices.zones.model.Coordinate;
import com.microservices.zones.model.PostCode;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.microservices.zones.service.PostCodeLookUpService;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

/**
 *
 * @author josem
 */
@Service
public class PostCodeLookUpServiceImpl implements PostCodeLookUpService {
    private static final Logger logger = LoggerFactory.getLogger(PostCodeLookUpServiceImpl.class);

    private final RestTemplate restTemplate;

    public PostCodeLookUpServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    @Override
    public Future<List<PostCode>> findPostCode(Coordinate coordinate) {
        logger.info("Looking up " + coordinate);
        String url = String.format("http://postcodes.io/postcodes?lon=%s&lat=%s", coordinate.getLongitude(), coordinate.getLatitude());
        Map<String, ?> results = restTemplate.getForObject(url, Map.class);
        List<PostCode> postcodes = (List<PostCode>) results.get("result");
        return new AsyncResult<>(postcodes);
    }
}
