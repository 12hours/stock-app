package com.epam.mentoring.routes.soapjr.model;

public class RequestObject {
    private RequestHeader header;
    private RequestBody body;

    public RequestHeader getHeader() {
        return header;
    }

    public RequestBody getBody() {
        return body;
    }

    public void setHeader(RequestHeader header) {
        this.header = header;
    }

    public void setBody(RequestBody body) {
        this.body = body;
    }
}
