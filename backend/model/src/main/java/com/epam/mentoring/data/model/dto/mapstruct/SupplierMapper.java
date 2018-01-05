package com.epam.mentoring.data.model.dto.mapstruct;

import com.epam.mentoring.data.model.Supplier;
import com.epam.mentoring.data.model.dto.view.SupplierView;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface SupplierMapper {

    @Mappings({
//            @Mapping(target = "links", expression = ("java( new java.util.HashMap<String, Object>())"))
    })
    SupplierView supplierToSupplierView(Supplier supplier);
}
