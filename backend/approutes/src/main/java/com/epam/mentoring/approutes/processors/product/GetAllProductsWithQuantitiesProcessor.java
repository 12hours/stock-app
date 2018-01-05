package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.data.model.dto.mapstruct.CollectionMapper;
import com.epam.mentoring.data.model.dto.view.ProductWithQuantityView;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import java.util.List;

public class GetAllProductsWithQuantitiesProcessor implements Processor {

    CollectionMapper<ProductWithQuantityView> collectionMapper = Mappers.getMapper(CollectionMapper.class);

    private ProductService productService;

    public GetAllProductsWithQuantitiesProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<ProductWithQuantityView> productsWithQuantitiesViews = productService.getAllProductsWithQuantitiesViews();

        exchange.getIn().setBody(collectionMapper.collectionToCollectionView(productsWithQuantitiesViews));
    }
}
