package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class GetProductWithQuantityProcessor implements Processor{

    private ProductService productService;

    public GetProductWithQuantityProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        ProductWithQuantityView productWithQuantity = productService.getProductWithQuantity(id);
        exchange.getIn().setBody(productWithQuantity);
    }
}
