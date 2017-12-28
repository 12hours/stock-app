package com.epam.mentoring.rest.controllers;

import org.apache.camel.ProducerTemplate;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public abstract class GenericRestController {

    protected Response handleRequest(ProducerTemplate route, Object body, Map<String, Object> headers) {
        String answer = (String) route.requestBodyAndHeaders(body, headers);
        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(answer)
                .build();
    }
}
