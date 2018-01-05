package com.epam.mentoring.approutes.processors.supplier;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.Collection;

public class GetIncomesOfSupplierProcessor implements Processor {
    private SupplierService supplierService;

    public GetIncomesOfSupplierProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Collection<ProductIncome> productIncomes = supplierService.getAllIncomesOfSupplier(id);
        exchange.getIn().setBody(productIncomes);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
