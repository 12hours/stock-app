package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.view.ProductView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductMapper {

    @Mapping(source = "product.type.id", target = "productTypeId")
    ProductView productToProductView(Product product);
}
