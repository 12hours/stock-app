package com.epam.mentoring.routes.processors.supplier;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;

public class GetSupplierByIdProcessor implements Processor {
    private SupplierService supplierService;

    public GetSupplierByIdProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Supplier supplier = supplierService.getSupplierById(id);
        exchange.getIn().setBody(supplier);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
