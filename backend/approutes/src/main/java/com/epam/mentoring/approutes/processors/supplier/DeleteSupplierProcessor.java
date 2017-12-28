package com.epam.mentoring.approutes.processors.supplier;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;

public class DeleteSupplierProcessor implements Processor {
    private SupplierService supplierService;

    public DeleteSupplierProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        supplierService.deleteSupplier(id);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
