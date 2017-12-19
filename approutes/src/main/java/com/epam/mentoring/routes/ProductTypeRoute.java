package com.epam.mentoring.routes;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.ProductTypeForm;
import com.epam.mentoring.routes.constants.Headers;
import com.epam.mentoring.routes.constants.RouteNames;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.List;

@Component
public class ProductTypeRoute extends RouteBuilder {

    @Autowired
    ProductTypeService productTypeService;

    @Override
    public void configure() throws Exception {
        from(RouteNames.PRODUCT_TYPE_ROUTE).routeId(RouteNames.PRODUCT_TYPE_ROUTE_ID)
                .choice()
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_ALL))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        List<ProductType> productTypes = productTypeService.getAllProductTypes();
                        exchange.getIn().setBody(productTypes);
                        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
                    }
                })
                .when(header(Headers.METHOD).isEqualTo(Headers.GET_BY_ID))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
                        ProductType productType = productTypeService.getProductTypeById(id);
                        exchange.getIn().setBody(productType);
                        exchange.getIn().setHeader(Headers.STATUS, Response.Status.OK);
                    }
                })
                .when(header(Headers.METHOD).isEqualTo(Headers.POST))
                .unmarshal().json(JsonLibrary.Jackson, ProductTypeForm.class)
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
