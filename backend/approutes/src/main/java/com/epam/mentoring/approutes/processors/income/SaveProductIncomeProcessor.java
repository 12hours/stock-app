package com.epam.mentoring.approutes.processors.income;

import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductIncomeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public class SaveProductIncomeProcessor implements Processor {
    private ProductIncomeService productIncomeService;

    public SaveProductIncomeProcessor(ProductIncomeService productIncomeService) {
        this.productIncomeService = productIncomeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        ProductIncomeForm productIncomeForm = (ProductIncomeForm) exchange.getIn().getBody();
        Integer id = productIncomeService.saveProductIncome(productIncomeForm);
        HashMap<String, Object> idMap = new HashMap<>();
        idMap.put("id", id);
        exchange.getIn().setBody(idMap);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.CREATED);
    }
}
