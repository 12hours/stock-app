package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.mapstruct.ProductMapper;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

public class GetProductOfProductIncomeProcessor implements Processor {
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private ProductService productService;

    public GetProductOfProductIncomeProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Product product = productService.getProductOfProductIncome(id);
        ProductView productView = productMapper.productToProductView(product);
        exchange.getIn().setBody(productView);
    }
}
