package com.epam.mentoring.routes.soapjr.deserialization;

import com.epam.mentoring.routes.soapjr.model.RequestBody;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class RequestDeserializer extends StdDeserializer<RequestBody> {

    public RequestDeserializer() {
        this(null);
    }

    protected RequestDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public RequestBody deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        return null;
    }
}
