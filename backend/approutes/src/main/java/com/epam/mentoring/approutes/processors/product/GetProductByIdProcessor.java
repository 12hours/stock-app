package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;

public class GetProductByIdProcessor implements Processor{
    private ProductService productService;

    public GetProductByIdProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);

        Product product = productService.findProductById(id);
        exchange.getIn().setBody(product);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
