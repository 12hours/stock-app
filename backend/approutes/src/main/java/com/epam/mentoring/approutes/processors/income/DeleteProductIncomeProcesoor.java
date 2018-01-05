package com.epam.mentoring.approutes.processors.income;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.ProductIncomeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;

public class DeleteProductIncomeProcesoor implements Processor {
    private ProductIncomeService productIncomeService;

    public DeleteProductIncomeProcesoor(ProductIncomeService productIncomeService) {
        this.productIncomeService = productIncomeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        productIncomeService.deleteProductIncome(id);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
