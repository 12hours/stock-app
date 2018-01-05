package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.processors.product.GetIncomesOfProductProcessor;
import com.epam.mentoring.approutes.processors.product.DeleteProductProcessor;
import com.epam.mentoring.approutes.processors.product.GetAllProductsWithQuantitiesProcessor;
import com.epam.mentoring.data.model.dto.ProductForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.approutes.processors.product.GetAllProductsProcessor;
import com.epam.mentoring.approutes.processors.product.GetProductByIdProcessor;
import com.epam.mentoring.approutes.processors.product.SaveProductProcessor;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@Component
public class ProductRoute extends RouteBuilder {

    @Autowired
    ProductService productService;

    @Value("${product.route.endpoint}")
    private String productRouteEndpoint;

    @Override
    public void configure() throws Exception {
        onException(NotFoundException.class)
                .handled(true)
                .to(RouteNames.NOT_FOUND_ROUTE);

        from(productRouteEndpoint).routeId(RouteNames.PRODUCT_ROUTE_ID)
                .log(LoggingLevel.DEBUG, "Method: " + header(Headers.OPERATION))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                    }
                })
                .choice()
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("GET"))
                        .to("direct:get")
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("POST"))
                        .to("direct:post")
                    .when(header(Exchange.HTTP_METHOD).isEqualTo("DELETE"))
                        .to("direct:delete")
                    .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal(new JsonDataFormat(JsonLibrary.Jackson))
                .convertBodyTo(String.class)
                .end();

        from("direct:get")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_ALL))
                        .process(new GetAllProductsProcessor(productService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_BY_ID))
                        .process(new GetProductByIdProcessor(productService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_ALL_WITH_QAUNT))
                        .process(new GetAllProductsWithQuantitiesProcessor(productService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_INCOMES))
                        .process(new GetIncomesOfProductProcessor(productService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .choice()
                    .when(body().isNull())
                        .throwException(new NotFoundException("Null object returned"))
                    .otherwise()
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.OK_200))
                .end();


        from("direct:post")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_POST))
                        .unmarshal().json(JsonLibrary.Jackson, ProductForm.class)
                        .process(new SaveProductProcessor(productService))
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.CREATED_201))
                    .otherwise().throwException(new UnsupportedOperationException())
                .end();


        from("direct:delete")
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_DELETE))
                        .process(new DeleteProductProcessor(productService))
                        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.OK_200))
                    .otherwise().throwException(new UnsupportedOperationException())
                .end();

    }
}
