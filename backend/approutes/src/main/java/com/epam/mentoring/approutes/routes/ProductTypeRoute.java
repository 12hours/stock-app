package com.epam.mentoring.approutes.routes;

import com.epam.mentoring.approutes.processors.type.GetProductsOfTypeProcessor;
import com.epam.mentoring.approutes.processors.type.DeleteProductTypeProcessor;
import com.epam.mentoring.data.model.dto.form.ProductTypeForm;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.approutes.constants.RouteNames;
import com.epam.mentoring.approutes.processors.type.GetAllProductTypesProcessor;
import com.epam.mentoring.approutes.processors.type.GetProductTypeByIdProcessor;
import com.epam.mentoring.approutes.processors.type.SaveProductTypeProcessor;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeRoute extends RouteBuilder {

    @Autowired
    ProductTypeService productTypeService;

    @Value("${productType.route.endpoint}")
    private String productTypeRouteEndpoint;

    @Override
    public void configure() throws Exception {
        from(productTypeRouteEndpoint).routeId(RouteNames.PRODUCT_TYPE_ROUTE_ID)
                .log(LoggingLevel.DEBUG, "Method: " + header(Headers.OPERATION))
                .choice()
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_GET_ALL))
                    .process(new GetAllProductTypesProcessor(productTypeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_GET_BY_ID))
                    .process(new GetProductTypeByIdProcessor(productTypeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_POST))
                    .unmarshal().json(JsonLibrary.Jackson, ProductTypeForm.class)
                    .process(new SaveProductTypeProcessor(productTypeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_DELETE))
                    .process(new DeleteProductTypeProcessor(productTypeService))
                .when(header(Headers.OPERATION).isEqualTo(Headers.PRODUCT_TYPE_PRODUCTS))
                    .process(new GetProductsOfTypeProcessor(productTypeService))
                .otherwise().throwException(new UnsupportedOperationException())
                .end()
                .marshal().json(JsonLibrary.Jackson)
                .convertBodyTo(String.class)
                .end();
    }
}
