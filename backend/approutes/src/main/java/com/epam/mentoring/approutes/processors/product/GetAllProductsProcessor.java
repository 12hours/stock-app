package com.epam.mentoring.approutes.processors.product;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.mapstruct.CollectionMapper;
import com.epam.mentoring.data.model.dto.mapstruct.ProductMapper;
import com.epam.mentoring.data.model.dto.view.ProductView;
import com.epam.mentoring.service.ProductService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

public class GetAllProductsProcessor implements Processor {

    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    private CollectionMapper<ProductView> productViewCollectionMapper = Mappers.getMapper(CollectionMapper.class);

    private ProductService productService;

    public GetAllProductsProcessor(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Product> allProducts = productService.getAllProducts();

        List<ProductView> productViews = allProducts.stream()
                        .map(product -> productMapper.productToProductView(product))
                        .collect(Collectors.toList());

        exchange.getIn().setBody(productViewCollectionMapper.collectionToCollectionView(productViews));
    }
}
