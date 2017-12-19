package com.epam.mentoring.routes.processors.supplier;

import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.data.model.dto.SupplierForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.HashMap;

public class SaveSupplierProcessor implements Processor {
    private SupplierService supplierService;

    public SaveSupplierProcessor(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        SupplierForm supplierForm = (SupplierForm) exchange.getIn().getBody();
        Integer id = supplierService.saveSupplier(supplierForm);
        HashMap<String, Integer> idMap = new HashMap<>();
        idMap.put("id", id);
        exchange.getIn().setBody(idMap);
        exchange.getIn().setHeader(Headers.STATUS, Response.Status.CREATED);
    }
}
