package com.epam.mentoring.routes.soapjr.deserialization;

import com.epam.mentoring.routes.soapjr.model.RequestBody;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class RequestBodyDeserializer extends StdDeserializer<RequestBody>{

    protected RequestBodyDeserializer(Class<?> vc) {
        super(vc);
    }

    protected RequestBodyDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public RequestBody deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        return null;
    }
}
