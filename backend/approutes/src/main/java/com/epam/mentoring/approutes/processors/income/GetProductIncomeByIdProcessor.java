package com.epam.mentoring.approutes.processors.income;

import com.epam.mentoring.data.model.ProductIncome;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.mapstruct.ProductIncomeMapper;
import com.epam.mentoring.service.ProductIncomeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.core.Response;

public class GetProductIncomeByIdProcessor implements Processor {
    private ProductIncomeMapper productIncomeMapper = Mappers.getMapper(ProductIncomeMapper.class);

    private ProductIncomeService productIncomeService;

    public GetProductIncomeByIdProcessor(ProductIncomeService productIncomeService) {
        this.productIncomeService = productIncomeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        ProductIncome productIncome = productIncomeService.getProductIncomeById(id);
        exchange.getIn().setBody(productIncomeMapper.productIncomeToProductIncomeView(productIncome));
    }
}
