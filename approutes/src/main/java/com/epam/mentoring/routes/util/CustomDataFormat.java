package com.epam.mentoring.routes.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.io.OutputStream;

public class CustomDataFormat implements DataFormat {

    @Autowired
    private ObjectMapper objectMapper;

    private Class clazz;

    public CustomDataFormat(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
//        exchange.getContext().getT

    }

    @Override
    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {

        Object body = objectMapper.readValue(exchange.getIn().getBody().toString(), clazz);
        return null;
    }
}
