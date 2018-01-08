package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.processors.type.*;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;

@Component
public class ProductTypeRoute extends RouteBuilder {

    @Autowired
    ProductTypeService productTypeService;

    @Override
    public void configure() throws Exception {

        from(RouteNames.PRODUCT_TYPE_ROUTE).routeId(RouteNames.PRODUCT_TYPE_ROUTE_ID)
                .log(LoggingLevel.DEBUG, "Method: " + header(Headers.OPERATION))
                .process(exchange -> {
                    System.out.println("debug");
                })
                .choice()
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("GET"))
                        .to("direct:productTypeGET")
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("POST"))
                        .to("direct:productTypePOST")
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("DELETE"))
                        .to("direct:productTypeDELETE")
                    .otherwise().throwException(new UnsupportedOperationException())
                .end();

        from("direct:productTypeGET")
                .choice()
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_GET_ALL))
                    .process(new GetAllProductTypesProcessor(productTypeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_GET_BY_ID))
                    .process(new GetProductTypeByIdProcessor(productTypeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_GET_PRODUCTS))
                    .process(new GetProductsOfTypeProcessor(productTypeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_TYPE))
                    .process(new GetProductTypeOfProductProcessor(productTypeService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .choice()
                    .when(body().isNull())
                        .throwException(new NotFoundException("Null object returned"))
                    .otherwise()
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.OK_200))
                .end();

        from("direct:productTypePOST")
                .choice()
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_POST))
                    .unmarshal().json(JsonLibrary.Jackson, ProductTypeForm.class)
                    .process(new SaveProductTypeProcessor(productTypeService))
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.CREATED_201))
                .otherwise().throwException(new UnsupportedOperationException())
                .end();

        from("direct:productTypeDELETE")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_DELETE))
                        .process(new DeleteProductTypeProcessor(productTypeService))
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.OK_200))
                .otherwise().throwException(new UnsupportedOperationException())
                .end();

    }
}
