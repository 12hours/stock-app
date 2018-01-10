package com.epam.mentoring.approutes.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.lang.reflect.Field;

public class View2HalSerializer extends StdSerializer {

    @Autowired
    private LinkBuilder linkBuilder;

    public View2HalSerializer() {
        this(null);
    }

    public View2HalSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        for (Field field : value.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                gen.writeObjectField(field.getName(), field.get(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        gen.writeObjectField("_links", linkBuilder.getLinks(value));
        gen.writeEndObject();
    }
}
