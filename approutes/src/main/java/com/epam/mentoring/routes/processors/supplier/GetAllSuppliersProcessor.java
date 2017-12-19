package com.epam.mentoring.routes.processors.supplier;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.List;

public class GetAllSuppliersProcessor implements Processor {

    private SupplierService supplierService;

    public GetAllSuppliersProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Supplier> allSuppliers = supplierService.getAllSuppliers();
        exchange.getIn().setBody(allSuppliers);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
    }
}
