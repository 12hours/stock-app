package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.routes.constants.RouteNames;
import com.epam.mentoring.routes.processors.producttype.GetAllProductTypesProcessor;
import com.epam.mentoring.routes.processors.producttype.GetProductTypeByIdProcessor;
import com.epam.mentoring.routes.processors.producttype.SaveProductTypeProcessor;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeRoute extends RouteBuilder {

    @Autowired
    ProductTypeService productTypeService;

    @Override
    public void configure() throws Exception {
        from(RouteNames.PRODUCT_TYPE_ROUTE).routeId(RouteNames.PRODUCT_TYPE_ROUTE_ID)
                .choice()
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_ALL))
                    .process(new GetAllProductTypesProcessor(productTypeService))
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_BY_ID))
                    .process(new GetProductTypeByIdProcessor(productTypeService))
                .when(header(Headers.METHOD).isEqualTo(Headers.POST))
                    .unmarshal().json(JsonLibrary.Jackson, ProductTypeForm.class)
                    .process(new SaveProductTypeProcessor(productTypeService))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                    }
                })
                .end()
                .marshal().json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .end();
    }
}