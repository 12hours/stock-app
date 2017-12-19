package com.epam.mentoring.approutes.processors.producttype;

import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public class SaveProductTypeProcessor implements Processor {

    private ProductTypeService productTypeService;

    public SaveProductTypeProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        ProductTypeForm productTypeForm = (ProductTypeForm) exchange.getIn().getBody();
        Integer id = productTypeService.saveProductType(productTypeForm);
        HashMap<String, Integer> idMap = new HashMap<>();
        idMap.put("id", id);
        exchange.getIn().setBody(idMap);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.CREATED);
    }
}
