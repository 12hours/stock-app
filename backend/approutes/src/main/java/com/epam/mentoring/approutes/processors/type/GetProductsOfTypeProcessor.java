package com.epam.mentoring.approutes.processors.type;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class GetProductsOfTypeProcessor implements Processor {
    private ProductTypeService productTypeService;

    public GetProductsOfTypeProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Collection<Product> products = productTypeService.getAllProductsOfType(id);
        exchange.getIn().setBody(products);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
