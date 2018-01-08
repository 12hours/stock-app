package com.epam.mentoring.approutes.processors.type;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.mapstruct.ProductTypeMapper;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

public class GetProductTypeOfProductProcessor implements Processor {

    private ProductTypeService productTypeService;

    private ProductTypeMapper productTypeMapper = Mappers.getMapper(ProductTypeMapper.class);

    public GetProductTypeOfProductProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        ProductType productType = productTypeService.getProductTypeForProduct(id);
        ProductTypeView productTypeView = productTypeMapper.productTypeToProductTypeView(productType);
        exchange.getIn().setBody(productTypeView);
    }
}
