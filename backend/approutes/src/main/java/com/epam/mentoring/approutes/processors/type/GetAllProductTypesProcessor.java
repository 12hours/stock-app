package com.epam.mentoring.approutes.processors.type;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.mapstruct.ProductTypeMapper;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import com.epam.mentoring.service.ProductTypeService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

public class GetAllProductTypesProcessor implements Processor {
    private ProductTypeMapper productTypeMapper = Mappers.getMapper(ProductTypeMapper.class);

    private ProductTypeService productTypeService;

    public GetAllProductTypesProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<ProductType> productTypes = productTypeService.getAllProductTypes();
        List<ProductTypeView> productTypeViews = productTypes.stream().map(productType -> productTypeMapper.productTypeToProductTypeView(productType)).collect(Collectors.toList());
        CollectionDTO<ProductTypeView> dto = new CollectionDTO<>(productTypeViews);
        exchange.getIn().setBody(dto);
    }
}
