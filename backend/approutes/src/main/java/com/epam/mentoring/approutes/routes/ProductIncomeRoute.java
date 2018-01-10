package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.approutes.processors.income.DeleteProductIncomeProcesoor;
import com.epam.mentoring.approutes.processors.income.GetProductIncomeByIdProcessor;
import com.epam.mentoring.approutes.processors.income.SaveProductIncomeProcessor;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;

@Component
public class ProductIncomeRoute extends RouteBuilder {

    @Autowired
    ProductIncomeService productIncomeService;

    @Override
    public void configure() throws Exception {

        onException(NotFoundException.class)
                .handled(true)
                .to(RouteNames.NOT_FOUND_ROUTE);


        from(RouteNames.PRODUCT_INCOME_ROUTE).routeId(RouteNames.PRODUCT_INCOME_ROUTE_ID)
                .to("log:" + this.getClass().getName() + "?level=DEBUG&showHeaders=true&showBody=false&showBodyType=false")
                .process(exchange -> {
                    System.out.println("debug");
                })
                .choice()
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("GET"))
                        .to("direct:productIncomeGET")
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("POST"))
                        .to("direct:productIncomePOST")
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("DELETE"))
                        .to("direct:productIncomeDELETE")
                    .otherwise().throwException(new UnsupportedOperationException())
                .end();

        from("direct:productIncomeGET")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_GET_BY_ID))
                        .process(new GetProductIncomeByIdProcessor(productIncomeService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_GET_PRODUCT))
                        .to(RouteNames.PRODUCT_ROUTE)
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_GET_SUPPLIER))
                        .to(RouteNames.SUPPLIER_ROUTE)
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_GET_USER))
                        .throwException(new NotFoundException("User not found"))// TODO: user route
                    .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .choice()
                    .when(body().isNull())
                        .throwException(new NotFoundException("Null object returned"))
                    .otherwise()
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.OK_200))
                .end();

        from("direct:productIncomePOST")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_POST))
                        .unmarshal().json(JsonLibrary.Jackson, ProductIncomeForm.class)
                        .process(new SaveProductIncomeProcessor(productIncomeService))
                    .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.CREATED_201));

        from("direct:productIncomeDELETE")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_DELETE))
                        .process(new DeleteProductIncomeProcesoor(productIncomeService))
                    .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.OK_200));

    }
}
