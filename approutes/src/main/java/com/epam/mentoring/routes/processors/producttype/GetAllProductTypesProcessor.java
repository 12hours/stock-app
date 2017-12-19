package com.epam.mentoring.routes.processors.producttype;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.List;

public class GetAllProductTypesProcessor implements Processor {
    private ProductTypeService productTypeService;

    public GetAllProductTypesProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        exchange.getIn().setBody(productTypes);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
