package com.epam.mentoring.routes.util;

import com.epam.mentoring.data.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import my.soapjr.model.RequestObject;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Scanner;

public class RequestObjectDataFormat implements DataFormat {

    @Autowired
    private ObjectMapper objectMapper;

    private Class<?> clazz;

    public RequestObjectDataFormat(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
//        exchange.getContext().getT

    }

    @Override
    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
//        Scanner s = new Scanner(stream).useDelimiter("\\A");
//        String result = s.hasNext() ? s.next() : "";

        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(stream, "UTF-8");
        for (; ; ) {
            int sz = in.read(buffer, 0, buffer.length);
            if (sz < 0)
                break;
            out.append(buffer, 0, sz);
        }
        String json = out.toString();
        RequestObject requestObject = objectMapper.readValue(json, RequestObject.class);
        return requestObject;
    }
}
