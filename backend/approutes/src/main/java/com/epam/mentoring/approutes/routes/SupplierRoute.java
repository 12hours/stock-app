package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.approutes.processors.supplier.*;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SupplierRoute extends RouteBuilder {

    @Autowired
    SupplierService supplierService;

    @Value("${supplier.route.endpoint}")
    private String supplierRouteEndpoint;

    @Override
    public void configure() throws Exception {
        from(supplierRouteEndpoint).routeId(RouteNames.SUPPLIER_ROUTE_ID)
                .log(LoggingLevel.DEBUG, "Method: " + header(Headers.OPERATION))
                .choice()
                .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_GET_ALL))
                    .process(new GetAllSuppliersProcessor(supplierService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_GET_BY_ID))
                    .process(new GetSupplierByIdProcessor(supplierService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_POST))
                    .unmarshal().json(JsonLibrary.Jackson, SupplierForm.class)
                    .process(new SaveSupplierProcessor(supplierService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_DELETE))
                    .process(new DeleteSupplierProcessor(supplierService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_INCOMES))
                    .process(new GetIncomesOfSupplierProcessor(supplierService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal().json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .end();
    }
}
