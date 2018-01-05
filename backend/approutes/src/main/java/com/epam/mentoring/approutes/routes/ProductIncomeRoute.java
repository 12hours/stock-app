package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.approutes.processors.income.DeleteProductIncomeProcesoor;
import com.epam.mentoring.approutes.processors.income.GetProductIncomeByIdProcessor;
import com.epam.mentoring.approutes.processors.income.SaveProductIncomeProcessor;
import com.epam.mentoring.data.model.dto.form.ProductIncomeForm;
import com.epam.mentoring.service.ProductIncomeService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductIncomeRoute extends RouteBuilder {

    @Autowired
    ProductIncomeService productIncomeService;

    @Value("${productIncome.route.endpoint}")
    private String productIncomeRouteEndpoint;

    @Override
    public void configure() throws Exception {
        from(productIncomeRouteEndpoint).routeId(RouteNames.PRODUCT_INCOME_ROUTE_ID)
                .to("log:" + this.getClass().getName() + "?level=DEBUG&showHeaders=true&showBody=false&showBodyType=false")
                .choice()
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_GET_BY_ID))
                   .process(new GetProductIncomeByIdProcessor(productIncomeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_POST))
                    .unmarshal().json(JsonLibrary.Jackson, ProductIncomeForm.class)
                    .process(new SaveProductIncomeProcessor(productIncomeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_INCOME_DELETE))
                    .process(new DeleteProductIncomeProcesoor(productIncomeService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal().json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .end();

    }
}
