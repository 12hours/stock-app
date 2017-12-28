package com.epam.mentoring.approutes.routes;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductRoute extends RouteBuilder {

    @Autowired
    ProductService productService;

    @Value("${product.route.endpoint}")
    private String productRouteEndpoint;

    @Override
    public void configure() throws Exception {
        from(productRouteEndpoint).routeId(RouteNames.PRODUCT_ROUTE_ID)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                    }
                })
                .log(LoggingLevel.DEBUG, "Method: " + header(Headers.OPERATION))
                .choice()
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_ALL))
                        .process(new GetAllProductsProcessor(productService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_BY_ID))
                        .process(new GetProductByIdProcessor(productService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_POST))
                        .unmarshal().json(JsonLibrary.Jackson, ProductForm.class)
                        .process(new SaveProductProcessor(productService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_GET_ALL_WITH_QAUNT))
                        .process(new GetAllProductsWithQuantitiesProcessor(productService))
                    .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_DELETE))
                        .process(new DeleteProductProcessor(productService))
                    .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal(new JsonDataFormat(JsonLibrary.Jackson))
                .convertBodyTo(String.class)
                .end();
    }
}