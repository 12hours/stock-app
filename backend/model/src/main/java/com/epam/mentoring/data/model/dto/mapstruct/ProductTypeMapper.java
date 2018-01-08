package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.ProductType;
import com.epam.mentoring.data.model.dto.view.ProductTypeView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductTypeMapper {

    ProductTypeView productTypeToProductTypeView(ProductType productType);
}
