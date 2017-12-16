package com.epam.mentoring.routes.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import my.soapjr.deserialization.reuest.RequestBodyDeserializer;
import my.soapjr.deserialization.reuest.RequestDeserializer;
import my.soapjr.deserialization.reuest.RequestHeadDeserializer;
import my.soapjr.model.RequestBody;
import my.soapjr.model.RequestHead;
import my.soapjr.model.RequestObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfig {

    @Bean
    @Primary
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(RequestBody.class, new RequestBodyDeserializer());
        simpleModule.addDeserializer(RequestHead.class, new RequestHeadDeserializer());
        simpleModule.addDeserializer(RequestObject.class, new RequestDeserializer());
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        return objectMapper;
    }
}
