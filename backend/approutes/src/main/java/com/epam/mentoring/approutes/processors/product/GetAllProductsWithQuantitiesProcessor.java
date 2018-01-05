package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import javax.ws.rs.core.Response;
import java.util.List;

public class GetAllProductsWithQuantitiesProcessor implements Processor {
    private ProductService productService;

    public GetAllProductsWithQuantitiesProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<ProductWithQuantityView> productsWithQuantitiesViews = productService.getAllProductsWithQuantitiesViews();
        exchange.getIn().setBody(productsWithQuantitiesViews);
    }
}
