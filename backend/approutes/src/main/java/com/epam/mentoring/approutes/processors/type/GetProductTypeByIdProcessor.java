package com.epam.mentoring.approutes.processors.type;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.mapstruct.ProductTypeMapper;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.core.Response;

public class GetProductTypeByIdProcessor implements Processor {

    private ProductTypeMapper productTypeMapper = Mappers.getMapper(ProductTypeMapper.class);

    private ProductTypeService productTypeService;

    public GetProductTypeByIdProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        ProductType productType = productTypeService.getProductTypeById(id);
        exchange.getIn().setBody(productTypeMapper.productTypeToProductTypeView(productType));
    }
}
