package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class GetIncomesOfProductProcessor implements Processor {
    private ProductService productService;

    public GetIncomesOfProductProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Collection<ProductIncome> productIncomes = productService.getIncomesOfProduct(id);
        exchange.getIn().setBody(productIncomes);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
