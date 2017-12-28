package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;

public class DeleteProductProcessor implements Processor {
    private ProductService productService;

    public DeleteProductProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        productService.deleteProductById(id);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
