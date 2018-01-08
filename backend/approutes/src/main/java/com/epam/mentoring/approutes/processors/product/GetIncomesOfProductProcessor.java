package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.mapstruct.ProductIncomeMapper;
import com.epam.mentoring.data.model.dto.view.ProductIncomeView;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.stream.Collectors;

public class GetIncomesOfProductProcessor implements Processor {
    private ProductIncomeMapper productIncomeMapper = Mappers.getMapper(ProductIncomeMapper.class);

    private ProductService productService;

    public GetIncomesOfProductProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Collection<ProductIncome> productIncomes = productService.getIncomesOfProduct(id);

        Collection<ProductIncomeView> productIncomeViews = productIncomes.stream()
                .map(productIncome -> productIncomeMapper.productIncomeToProductIncomeView(productIncome))
                .collect(Collectors.toList());

        CollectionDTO<ProductIncomeView> dto = new CollectionDTO<>(productIncomeViews);
        exchange.getIn().setBody(dto);
    }
}
