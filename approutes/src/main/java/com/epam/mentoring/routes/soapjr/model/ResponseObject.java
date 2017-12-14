package com.epam.mentoring.routes.soapjr.model;

public class ResponseObject {
    private ResponseHeader header;
    private ResponseBody body;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }
}
