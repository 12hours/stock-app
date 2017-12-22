package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.processors.productincome.DeleteProductIncomeProcesoor;
import com.epam.mentoring.data.model.dto.ProductIncomeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.approutes.processors.productincome.GetProductIncomeByIdProcessor;
import com.epam.mentoring.approutes.processors.productincome.SaveProductIncomeProcessor;
import com.epam.mentoring.service.ProductIncomeService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductIncomeRoute extends RouteBuilder {

    @Autowired
    ProductIncomeService productIncomeService;

    @Override
    public void configure() throws Exception {
        from(RouteNames.PRODUCT_INCOME_ROUTE).routeId(RouteNames.PRODUCT_INCOME_ROUTE_ID)
                .choice()
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_BY_ID))
                   .process(new GetProductIncomeByIdProcessor(productIncomeService))
                .when(header(Headers.METHOD).isEqualTo(Headers.POST))
                    .unmarshal().json(JsonLibrary.Jackson, ProductIncomeForm.class)
                    .process(new SaveProductIncomeProcessor(productIncomeService))
                .when(header(Headers.METHOD).isEqualTo(Headers.DELETE))
                    .process(new DeleteProductIncomeProcesoor(productIncomeService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal().json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .end();

    }
}
