package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.List;

public class GetAllProductsProcessor implements Processor {
    private ProductService productService;

    public GetAllProductsProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Product> allProducts = productService.getAllProducts();
        exchange.getIn().setBody(allProducts);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
