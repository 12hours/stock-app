package com.epam.mentoring.approutes.processors.producttype;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;

public class DeleteProductTypeProcessor implements Processor {
    private ProductTypeService productTypeService;

    public DeleteProductTypeProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        productTypeService.deleteProductType(id);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
