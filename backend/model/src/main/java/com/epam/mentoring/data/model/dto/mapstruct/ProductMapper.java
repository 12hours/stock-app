package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.Product;
import com.epam.mentoring.data.model.dto.view.ProductView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "product.type.id", target = "productTypeId"),
//            @Mapping(target = "links", expression = ("java( new java.util.HashMap<String, Object>())"))
    })
    ProductView productToProductView(Product product);
}
