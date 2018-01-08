package com.epam.mentoring.approutes.processors.type;

import com.epam.mentoring.approutes.constants.Headers;
import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.CollectionDTO;
import com.epam.mentoring.data.model.dto.mapstruct.ProductMapper;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.service.ProductTypeService;
import jdk.nashorn.internal.runtime.PropertyMap;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GetProductsOfTypeProcessor implements Processor {
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private ProductTypeService productTypeService;

    public GetProductsOfTypeProcessor(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Integer id = (Integer) exchange.getIn().getHeader(Headers.ID);
        Collection<Product> products = productTypeService.getAllProductsOfType(id);
        List<ProductView> productViews = products.stream().map(product -> productMapper.productToProductView(product)).collect(Collectors.toList());
        CollectionDTO<ProductView> dto = new CollectionDTO<>(productViews);

        exchange.getIn().setBody(dto);
    }
}
