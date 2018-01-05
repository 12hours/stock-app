package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductTypeMapper {

    @Mappings({
//            @Mapping(target = "links", expression = ("java( new java.util.HashMap<String, Object>())"))
    })
    ProductTypeView productTypeToProductTypeView(ProductType productType);
}
