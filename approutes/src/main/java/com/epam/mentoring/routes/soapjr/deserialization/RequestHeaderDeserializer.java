package com.epam.mentoring.routes.soapjr.deserialization;

import com.epam.mentoring.routes.soapjr.model.RequestHeader;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class RequestHeaderDeserializer extends StdDeserializer<RequestHeader> {

    protected RequestHeaderDeserializer(Class<?> vc) {
        super(vc);
    }

    protected RequestHeaderDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public RequestHeader deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
