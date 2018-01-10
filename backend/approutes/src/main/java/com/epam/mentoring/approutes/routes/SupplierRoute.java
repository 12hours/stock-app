package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.approutes.processors.supplier.*;
import com.epam.mentoring.data.model.dto.form.SupplierForm;
import com.epam.mentoring.service.SupplierService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SupplierRoute extends RouteBuilder {

    @Autowired
    SupplierService supplierService;

    @Override
    public void configure() throws Exception {

        from(RouteNames.SUPPLIER_ROUTE).routeId(RouteNames.SUPPLIER_ROUTE_ID)

                .to("log:" + this.getClass().getName() + "?level=DEBUG&showHeaders=true&showBody=false&showBodyType=false")
                .process(exchange -> {
                    System.out.println("debug");
                })
                .choice()
                .when(header(Exchange.HTTP_METHOD).isEqualTo("GET"))
                .to("direct:supplierGET")
                .when(header(Exchange.HTTP_METHOD).isEqualTo("POST"))
                .to("direct:supplierPOST")
                .when(header(Exchange.HTTP_METHOD).isEqualTo("DELETE"))
                .to("direct:supplierDELETE")
                .otherwise().throwException(new UnsupportedOperationException())
                .end();


        from("direct:supplierGET")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_GET_ALL))
                        .process(new GetAllSuppliersProcessor(supplierService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_GET_BY_ID))
                        .process(new GetSupplierByIdProcessor(supplierService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_GET_INCOMES))
                        .process(new GetIncomesOfSupplierProcessor(supplierService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_GET_SUPPLIER))
                        .process(new GetSupplierOfProductIncome(supplierService))
                    .otherwise().throwException(new UnsupportedOperationException())
                .end();

        from("direct:supplierPOST")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_POST))
                        .unmarshal().json(JsonLibrary.Jackson, SupplierForm.class)
                        .process(new SaveSupplierProcessor(supplierService))
                .otherwise().throwException(new UnsupportedOperationException());

        from("direct:supplierDELETE")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.SUPPLIER_DELETE))
                    .process(new DeleteSupplierProcessor(supplierService))
                .otherwise().throwException(new UnsupportedOperationException());
    }
}
