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
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductRoute extends RouteBuilder {

    @Autowired
    ProductService productService;

    @Override
    public void configure() throws Exception {
        from(RouteNames.PRODUCT_ROUTE).routeId(RouteNames.PRODUCT_ROUTE_ID)
                .choice()
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_ALL))
                   .process(new GetAllProductsProcessor(productService))
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_BY_ID))
                    .process(new GetProductByIdProcessor(productService))
                .when(header(Headers.METHOD).isEqualTo(Headers.POST))
                    .unmarshal().json(JsonLibrary.Jackson, ProductForm.class)
                    .process(new SaveProductProcessor(productService))
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_ALL_WITH_QAUNT))
                    .process(new GetAllProductsWithQuantitiesProcessor(productService))
                .when(header(Headers.METHOD).isEqualTo(Headers.DELETE))
                    .process(new DeleteProductProcessor(productService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal(new JsonDataFormat(JsonLibrary.Jackson))
                .convertBodyTo(String.class)
                .end();
    }
}
