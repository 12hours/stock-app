package com.epam.mentoring.web.client.impl;

import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.web.client.exception.ServerDataAccessException;
import com.epam.mentoring.web.client.exception.ServerResponseParsingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@Slf4j
public abstract class GenericCRUDRestClient {


    private RestTemplate restTemplate;
    private ObjectMapper objectMapper;

    public GenericCRUDRestClient(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    protected CollectionDTO getCollection(String uri, TypeReference typeReference) {
        log.debug("Getting collection");
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
            try {
                CollectionDTO dto = objectMapper.readValue(response.getBody(), typeReference);
                return dto;
            } catch (IOException e) {
                log.error("Can not parse server response: {}", e);
                throw new ServerDataAccessException("Can not parse server response", e);
            }
        } catch (RestClientException e) {
            log.error("Can not get data from server: {}", e);
            throw new ServerDataAccessException("Can not get data from server", e);
        }
    }

    protected Integer saveObject(String uri, Object object) {
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(uri, object, String.class);
            try {
                Map<String, Integer> idMap = objectMapper.readValue(response.getBody(), new TypeReference<Map<String, Integer>>() {});
                return idMap.get("id");
            } catch (IOException e) {
                log.error("Can not recognize server response: {}", e);
                throw new ServerResponseParsingException("Can not recognize server response", e);
            }
        } catch (RestClientException ex) {
            log.error("Can not save data: {}", ex);
            throw new ServerDataAccessException("Can not save data to server", ex);
        }
    }

    protected Object findObject(String uri) {
        return null;
    }

    protected void deleteObject(String uri) {

    }

    protected void updateObject(String uri, Object object) {

    }

}
